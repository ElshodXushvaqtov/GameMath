package com.example.mathgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.mathgame.navigation.NavGraph

@Composable
fun OperatorScreen(navController: NavHostController) {


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {},
            Modifier
                .width(70.dp)
                .height(70.dp),
            shape = CircleShape
        ) {

            Text(text = "+", fontSize = 20.sp)

        }
        Row {
            Button(
                onClick = {},
                Modifier
                    .width(70.dp)
                    .height(70.dp),
                shape = CircleShape
            ) {

                Text(text = "-", fontSize = 20.sp)

            }
            Spacer(Modifier.width(20.dp))
            Button(
                onClick = {},
                Modifier
                    .width(70.dp)
                    .height(70.dp),
                shape = CircleShape
            ) {

                Text(text = "*", fontSize = 20.sp)

            }
        }
        Button(
            onClick = {},
            Modifier
                .width(70.dp)
                .height(70.dp),
            shape = CircleShape
        ) {

            Text(text = "/", fontSize = 20.sp)

        }

    }

}


@Preview
@Composable
fun test1() {

    val navController = rememberNavController()
    NavGraph(navController = navController)
    OperatorScreen(navController = navController)

}