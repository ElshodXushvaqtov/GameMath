package com.example.mathgame.navigation

const val OPERATOR_KEY = "operator_key"

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object IntroScreen : Screens("intro_screen")
    object OperatorsScreen : Screens("operators_screen")
    object RecordsScreen : Screens("records_screen")
    object DetailsScreen : Screens("details_screen")
    object NewGameScreen : Screens(route = "new_game_screen/{$OPERATOR_KEY}"){
        fun getFullRoute(operator: String): String {
            return "new_game_screen/$operator"
        }
    }
}