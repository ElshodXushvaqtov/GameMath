package com.example.mathgame.brain

import kotlin.random.Random

class Logic {

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


}