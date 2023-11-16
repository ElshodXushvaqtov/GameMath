package com.example.mathgame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.mathgame.navigation.NavGraph
import kotlin.random.Random

@Composable
fun NewGameScreen(navController: NavHostController) {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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

@Composable
fun AnswerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
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

