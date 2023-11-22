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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mathgame.brain.Logic
import com.example.mathgame.navigation.NavGraph
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun NewGameScreen(navController: NavHostController) {
    var isGameOver = false
    var num1 by remember { mutableIntStateOf(0) }
    var num2 by remember { mutableIntStateOf(0) }
    var operator = "+"
    var userAnswer by remember { mutableStateOf("") }
    val test = remember {
        mutableStateOf(Logic.generate())
    }
    var changeQuestion by remember { mutableStateOf(false) }
    if (changeQuestion) {
        num1 = Random.nextInt(1, 10)
        num2 = Random.nextInt(1, 10)
        operator = "+"
        userAnswer = ""
        changeQuestion = false
    }

    var score = remember { mutableIntStateOf(0) }
    val correctAnswer = num1 + num2
    val incorrectAnswers = Logic.incorrectAnswers(correctAnswer)
    val answerOptions =
        (listOf(correctAnswer) + incorrectAnswers).shuffled() //shuffled() - elementlarni random joylashtirib beradi

    var timerValue by remember { mutableIntStateOf(10) }
    LaunchedEffect(timerValue) {

        while (timerValue > 0) {
            delay(1000)
            timerValue--
        }

        isGameOver = true

    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

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
        }




        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Score: ${score.intValue}")
        Spacer(modifier = Modifier.height(16.dp))
        Text("$num1 $operator $num2 = ?")
        for (answer in answerOptions) {
            AnswerButton(
                text = answer.toString()
            ) {
                Logic.checkAnswer(answer, correctAnswer) { result ->
                    changeQuestion = result
                    if (result) {
                        score.intValue++
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

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


@Preview
@Composable
fun test() {

    val navController = rememberNavController()
    NavGraph(navController = navController)
    NewGameScreen(navController = navController)


}