package com.android.diarystud.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.diarystud.MainViewModel
import com.android.diarystud.model.Folder
import com.android.diarystud.model.Note
import com.android.diarystud.navigation.NavRoute
import com.android.diarystud.screens.elements.DeleteDialog
import com.android.diarystud.screens.elements.DiaryTopAppBar
import com.android.diarystud.screens.elements.DrawerAddFolder
import com.android.diarystud.screens.elements.DrawerBody
import com.android.diarystud.screens.elements.utils.Constants.Keys.DEFAULT_FOLDER_NAME
import kotlinx.coroutines.launch

@Composable
fun DiaryScreen(navController: NavHostController,
                viewModel: MainViewModel,
                folderId: String?
) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val folders = viewModel.readAllFolders().observeAsState(listOf()).value

    var fldId by remember { mutableStateOf(folderId!!.toInt()) }
    var chosenFolder by remember { mutableStateOf(Folder(name = DEFAULT_FOLDER_NAME)) }
    var folder by remember {
        mutableStateOf( folders.firstOrNull { it.id == fldId } ?: Folder(name = DEFAULT_FOLDER_NAME))
    }

    if (fldId != 0){ //TODO("А здесь что делать?")
        folder = folders.firstOrNull { it.id == fldId } ?: Folder(name = DEFAULT_FOLDER_NAME)
    }

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var deleteDialogIsOpen by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route + "/${folder.id}")
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add icons",
                    tint = Color.White
                )
            }
        },
        topBar = {
            DiaryTopAppBar(title = folder.name){
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = {
                    DrawerBody(
                        folders = folders,
                        modifier = Modifier.padding(it),
                        scaffoldState = scaffoldState,
                        onItemClick = { fld ->
                            folder = fld
                            fldId = folder.id
                            coroutineScope.launch {
                                scaffoldState.drawerState.close()
                            }
                        },
                        onDeleteClick = {
                            deleteDialogIsOpen = true
                            chosenFolder = it
                        },
                        onUpdateClick = { fld ->
                            navController.navigate(NavRoute.UpdateFolder.route + "/${fld.id}")
                        }
                    )
                },
                bottomBar = {
                    DrawerAddFolder{
                        navController.navigate(NavRoute.AddFolder.route + "/${folder.id}")
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(notes) { note ->
                if (note.folder == folder.id) {
                    NoteItem(note = note, navController = navController)
                }
            }
        }
        if (deleteDialogIsOpen){
            DeleteDialog(
                onConfirmClick = {
                    if (fldId == chosenFolder.id){
                        fldId = 0
                        folder = Folder(name = DEFAULT_FOLDER_NAME)
                    }
                    viewModel.deleteFolder(chosenFolder){}
                    deleteDialogIsOpen = false
                },
                onDismiss = { deleteDialogIsOpen = false}
            )
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${note.folder}/${note.id}")
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