package com.example.mathgame.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object IntroScreen : Screens("intro_screen")
    object OperatorsScreen : Screens("operators_screen")
    object RecordsScreen : Screens("records_screen")
    object DetailsScreen : Screens("details_screen")
    object NewGameScreen : Screens("new_game_screen")
}