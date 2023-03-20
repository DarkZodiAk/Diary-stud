package com.android.diarystud.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.diarystud.MainViewModel
import com.android.diarystud.MainViewModelFactory
import com.android.diarystud.navigation.NavRoute
import com.android.diarystud.ui.theme.DiaryStudTheme
import com.android.diarystud.utils.Constants
import com.android.diarystud.utils.Constants.Keys.AUTH_WITH_GOOGLE
import com.android.diarystud.utils.TYPE_ROOM
import com.ramcosta.composedestinations.annotation.Destination


@Destination(start = true)
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val mViewModel: MainViewModel = 
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

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
            Text(text = Constants.Keys.WELCOME)
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_ROOM){
                        navController.navigate(route = NavRoute.Diary.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = AUTH_WITH_GOOGLE)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    DiaryStudTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        StartScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}