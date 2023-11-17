package com.example.mathgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mathgame.navigation.NavGraph

@Composable
fun IntroScreen(navController: NavHostController) {


    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate("details_screen")
            },
            Modifier
                .width(300.dp)
                .height(100.dp),
            shape = CutCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(

                containerColor = Color(0xFFFF5925)

            )
        ) {

            Text(text = "New Game", fontSize = 20.sp)

        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick =
            {

                navController.navigate("records_screen")

            },
            Modifier
                .width(300.dp)
                .height(100.dp),
            shape = RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp),
            colors = ButtonDefaults.buttonColors(

                containerColor = Color(0xFF1B54FF)

            )
        ) {

            Text(text = "Records", fontSize = 20.sp)

        }
    }


}


@Preview
@Composable
fun UI() {

    val navController = rememberNavController()
    NavGraph(navController = navController)
    IntroScreen(navController = navController)


}