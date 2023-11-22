package com.example.mathgame.brain

import kotlin.random.Random
import kotlin.random.nextInt

class Logic private constructor(
    var a: Int,
    var b: Int,
    var c: Int,
    var operator: String
) {
    companion object {

        fun generate(): Logic {

            var num1: Int = Random.nextInt(1..20)
            var num2: Int = Random.nextInt(1..20)
            var answer: Int = 0
            var operator: String = ""

            val operators = mutableListOf<String>("+", "-", "/", "*")
            operator = operators.random()

            when (operator) {
                "+" -> {
                    answer = num1 + num2
                }

                "-" -> {
                    answer = num1 - num2
                }

                "*" -> {
                    answer = num1 * num2
                }

                "/" -> {
                    answer = num1 / num2
                }
            }
            return Logic(a = num1, b = num2, c = answer, operator = operator)
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


    }
}