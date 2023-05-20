package com.android.diarystud.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.diarystud.MainViewModel
import com.android.diarystud.model.Folder
import com.android.diarystud.navigation.NavRoute
import com.android.diarystud.screens.elements.FolderTopAppBar
import com.android.diarystud.screens.elements.utils.Constants

@Composable
fun AddFolderScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    folderId: String?
) {
    var title by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            FolderTopAppBar(
                title = Constants.Keys.ADD_FOLDER,
                isButtonEnabled = isButtonEnabled,
                onCloseClick = { navController.navigate(NavRoute.Diary.route + "/$folderId") },
                onDoneClick = {
                    viewModel.addFolder(folder = Folder(name = title)){ fldId ->
                        navController.navigate(NavRoute.Diary.route + "/$fldId")
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

@Composable
fun UpdateFolderScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    folderId: String?
) {
    val folders = viewModel.readAllFolders().observeAsState(listOf()).value
    val folder = folders.firstOrNull { it.id == folderId?.toInt() } ?: Folder(id = -1, name = "")
    //TODO("Ну явный костыль, не?")

    var title by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(true) }

    if (folder.id != -1){
        title = folder.name
    }

    Scaffold(
        topBar = {
            FolderTopAppBar(
                title = Constants.Keys.UPDATE_FOLDER,
                isButtonEnabled = isButtonEnabled,
                onCloseClick = { navController.navigate(NavRoute.Diary.route + "/$folderId") },
                onDoneClick = {
                    viewModel.updateFolder(folder = Folder(id = folder.id, name = title)){
                        navController.navigate(NavRoute.Diary.route + "/$folderId")
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