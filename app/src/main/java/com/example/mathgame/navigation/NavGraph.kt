package com.example.mathgame.navigation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mathgame.DetailsScreen
import com.example.mathgame.IntroScreen
import com.example.mathgame.NewGameScreen
import com.example.mathgame.OperatorScreen
import com.example.mathgame.RecordsScreen
import com.example.mathgame.SplashScreen
import com.example.mathgame.module.SharedView

@Composable
fun NavGraph(navController: NavHostController) {

    val sharedView: SharedView = viewModel()

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
        composable(route = Screens.OperatorsScreen.route) {
            OperatorScreen(navController)
        }
        composable(route = Screens.RecordsScreen.route) {
            RecordsScreen(navController)
        }
        composable(route = Screens.DetailsScreen.route) {
            DetailsScreen(navController)
        }
        composable(route = Screens.NewGameScreen.route,
            arguments = listOf(
                navArgument(name = OPERATOR_KEY) {
                    type = NavType.StringType
                }
            )) { navBackStackEntry ->
            val sign = navBackStackEntry.arguments?.getString(OPERATOR_KEY)
            if (sign != null) {
                NewGameScreen(navController = navController, sign = sign)
            }

        }
    }
}

