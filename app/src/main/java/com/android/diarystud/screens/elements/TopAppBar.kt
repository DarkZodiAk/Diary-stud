package com.android.diarystud.screens.elements


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable


@Composable
fun DiaryTopAppBar(title: String, onNavigationIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle drawer")
            }
        }
    )
}

@Composable
fun FolderTopAppBar(title: String, onCloseClick: () -> Unit, onDoneClick: () -> Unit){
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = { onCloseClick() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Go back to notes")
            }
        },
        actions = {
            IconButton( onClick = { onDoneClick() } ){
                Icon(imageVector = Icons.Default.Done, contentDescription = "Done")
            }
        }
    )
}







