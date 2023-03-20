package com.android.diarystud.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.diarystud.MainViewModel
import com.android.diarystud.MainViewModelFactory
import com.android.diarystud.model.Note
import com.android.diarystud.navigation.NavRoute
import com.android.diarystud.ui.theme.DiaryStudTheme
import com.android.diarystud.utils.Constants
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AddScreen(navController: NavHostController,
              viewModel: MainViewModel,
              folderId: Int
) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    Scaffold {
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = Constants.Keys.ADD_NEW_NOTE,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = Constants.Keys.NOTE_TITLE) },
                isError = title.isEmpty()
            )
            OutlinedTextField(
                value = subtitle,
                onValueChange = {
                    subtitle = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = Constants.Keys.NOTE_SUBTITLE) },
                isError = subtitle.isEmpty()
            )
            Button (
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = Note(title = title, subtitle = subtitle, folder = folderId)) {
                        navController.navigate(NavRoute.Diary.route)
                    }
                }
            ) {
                Text(text = Constants.Keys.ADD_NOTE)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen() {
    DiaryStudTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        AddScreen(navController = rememberNavController(), viewModel = mViewModel, folderId = 0)
    }
}