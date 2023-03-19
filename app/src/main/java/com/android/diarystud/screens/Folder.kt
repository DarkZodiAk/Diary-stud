package com.android.diarystud.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.diarystud.MainViewModel
import com.android.diarystud.navigation.NavRoute
import com.android.diarystud.screens.elements.FolderTopAppBar
import com.android.diarystud.utils.Constants.Keys.ADD_FOLDER

@Composable
fun AddFolderScreen(navController: NavHostController, viewModel: MainViewModel) {
    var title by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            FolderTopAppBar(
                title = ADD_FOLDER,
                onCloseClick = { navController.navigate(NavRoute.Diary.route) },
                onDoneClick = { TODO("Чел, подумай над тем, а может лучше сюды весь топбар запихать, а то не очень хочется кучу параметров передавать") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(vertical = 8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}