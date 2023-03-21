package com.android.diarystud.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.android.diarystud.screens.elements.DiaryTopAppBar
import com.android.diarystud.screens.elements.DrawerAddFolder
import com.android.diarystud.screens.elements.DrawerBody
import com.android.diarystud.ui.theme.DiaryStudTheme
import com.android.diarystud.utils.Constants.Keys.DEFAULT_FOLDER_NAME
import kotlinx.coroutines.launch

@Composable
fun DiaryScreen(navController: NavHostController, viewModel: MainViewModel) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val folders = viewModel.readAllFolders().observeAsState(listOf()).value
    val folderId by remember { mutableStateOf(0) } // Да да, хардкодим
    val folderName by remember { mutableStateOf(DEFAULT_FOLDER_NAME) }

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route + "/${folderId}")
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add icons",
                    tint = Color.White
                )
            }
        },
        topBar = {
            DiaryTopAppBar(title = folderName){
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = {
                    DrawerBody(folders = folders, modifier = Modifier.padding(it)){ fld ->
                        Log.d("Folder_NAV","Clicked at folder ${fld.name}")
                    }
                },
                bottomBar = {
                    DrawerAddFolder{
                        navController.navigate(NavRoute.AddFolder.route)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(notes) { note ->
                NoteItem(note = note, navController = navController, folderId)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController, folderId: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${folderId}/${note.id}")
            },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = note.subtitle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevDiaryScreen() {
    DiaryStudTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        DiaryScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}