package com.android.diarystud.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.diarystud.navigation.NavRoute
import com.android.diarystud.ui.theme.DiaryStudTheme


@Composable
fun StartScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Добро пожаловать!")
            Button(
                onClick = {
                          navController.navigate(route = NavRoute.Diary.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Войти через Google")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevStartScreen(){
    DiaryStudTheme() {
        StartScreen(navController = rememberNavController())
    }
}