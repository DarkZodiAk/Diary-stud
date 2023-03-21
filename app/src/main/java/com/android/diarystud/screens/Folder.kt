package com.android.diarystud.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.diarystud.MainViewModel
import com.android.diarystud.model.Folder
import com.android.diarystud.screens.destinations.DiaryScreenDestination
import com.android.diarystud.screens.elements.FolderTopAppBar
import com.android.diarystud.utils.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AddFolderScreen(navigator: DestinationsNavigator, viewModel: MainViewModel) {
    var title by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            FolderTopAppBar(
                title = Constants.Keys.ADD_FOLDER,
                isButtonEnabled = isButtonEnabled,
                onCloseClick = { navigator.navigate(DiaryScreenDestination) },
                onDoneClick = {
                    viewModel.addFolder(folder = Folder(name = title)){
                        navigator.navigate(DiaryScreenDestination)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnabled = title.isNotEmpty()
                },
                label = { Text(text = Constants.Keys.FOLDER_TITLE) },
                isError = title.isEmpty()
            )
        }
    }
}