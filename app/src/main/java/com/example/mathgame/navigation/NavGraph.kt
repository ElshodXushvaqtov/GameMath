package com.example.mathgame.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mathgame.DetailsScreen
import com.example.mathgame.IntroScreen
import com.example.mathgame.NewGameScreen
import com.example.mathgame.OperatorScreen
import com.example.mathgame.RecordsScreen
import com.example.mathgame.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    )
    {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Screens.IntroScreen.route) {
         IntroScreen(navController)
        }
        composable(route = Screens.OperatorScreen.route) {
            OperatorScreen(navController)
        }
        composable(route = Screens.RecordsScreen.route) {
            RecordsScreen(navController)
        }
        composable(route = Screens.DetailsScreen.route) {
            DetailsScreen(navController)
        }
        composable(route = Screens.NewGameScreen.route) {
            NewGameScreen(navController)
        }
    }
}

