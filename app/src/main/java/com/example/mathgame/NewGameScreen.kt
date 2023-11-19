package com.example.mathgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mathgame.navigation.NavGraph
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random


class TimerViewModel : ViewModel() {
    private val _timerValue = MutableStateFlow(10)
    val timerValue: StateFlow<Int> = _timerValue.asStateFlow()

    private var timerJob: Job? = null
    var isGameOver by mutableStateOf(false)

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timerValue.value > 0) {
                delay(1000)
                _timerValue.value--
            }
            isGameOver = true
        }
    }

    fun resetTimer() {
        timerJob?.cancel()
        _timerValue.value = 10
        isGameOver = false
    }

    fun ifTimerEnds(onTimerEnds: () -> Unit) {
        if (isGameOver) {
            onTimerEnds()
            isGameOver = false // Reset the game over state after handling it
        }
    }
}


@Composable
fun NewGameScreen(navController: NavHostController, timerViewModel: TimerViewModel = viewModel()) {
    var num1 by remember { mutableIntStateOf(0) }
    var num2 by remember { mutableIntStateOf(0) }
    var operator = "+"
    var userAnswer by remember { mutableStateOf("") }

    var changeQuestion by remember { mutableStateOf(false) }

    if (changeQuestion) {
        num1 = Random.nextInt(1, 10)
        num2 = Random.nextInt(1, 10)
        operator = "+"
        userAnswer = ""
        changeQuestion = false
    }

    val correctAnswer = num1 + num2
    val incorrectAnswers = incorrectAnswers(correctAnswer)
    val answerOptions =
        (listOf(correctAnswer) + incorrectAnswers).shuffled() //shuffled() - elementlarni random joylashtirib beradi


    var isTimerRunning by remember { mutableStateOf(false) }
    val viewModel: TimerViewModel = remember { timerViewModel }
    val timerValue by viewModel.timerValue.collectAsState()


    LaunchedEffect(timerValue) {
        println("Timer value in UI: $timerValue")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        if (viewModel.isGameOver) {
            Text(text = "Game Over")
        } else {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Timer: $timerValue seconds",
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = {
                        if (!isTimerRunning) {
                            isTimerRunning = true
                            viewModel.startTimer()
                        }
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(50.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Text("Start Timer")
                }

                Button(
                    onClick = {
                        isTimerRunning = false
                        viewModel.resetTimer()
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(50.dp)
                ) {
                    Text("Reset Timer")
                }
            }



            Spacer(modifier = Modifier.height(40.dp))

            Text("$num1 $operator $num2 = ?")

            Spacer(modifier = Modifier.height(16.dp))

            for (answer in answerOptions) {
                AnswerButton(
                    text = answer.toString()
                ) {
                    checkAnswer(answer, correctAnswer) { result ->
                        changeQuestion = result
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}

@Composable
fun AnswerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .wrapContentWidth()
            .height(50.dp)
    ) {
        Text(text)
    }
}

fun checkAnswer(selectedAnswer: Int, correctAnswer: Int, onResult: (Boolean) -> Unit) {
    val isCorrect = selectedAnswer == correctAnswer

    onResult(isCorrect)
}

fun incorrectAnswers(correctAnswer: Int): List<Int> {
    val incorrectAnswers = mutableListOf<Int>()
    repeat(3) {
        var incorrectAnswer = Random.nextInt(1, 20)
        while (incorrectAnswer == correctAnswer) {    //random xato javob to'g'ri javobga teng bob qolmasligi uchun
            incorrectAnswer = Random.nextInt(1, 20)
        }
        incorrectAnswers.add(incorrectAnswer)
    }
    return incorrectAnswers
}


@Preview
@Composable
fun test() {

    val navController = rememberNavController()
    NavGraph(navController = navController)
    NewGameScreen(navController = navController)


}




