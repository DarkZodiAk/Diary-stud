package com.android.diarystud.screens.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.diarystud.model.Folder
import com.android.diarystud.utils.Constants.Keys.ADD_FOLDER


@Composable
fun DrawerBody(
    folders: List<Folder>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (Folder) -> Unit
) {
    LazyColumn(modifier){
        items(folders){ folder ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(folder)
                }
                .padding(16.dp)
            ){
                Text(
                    text = folder.name,
                    style = itemTextStyle
                )
            }
        }
    }
}

@Composable
fun DrawerAddFolder(onButtonClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onButtonClick()
            }
            .padding(16.dp)
    ){
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add folder"
            //tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = ADD_FOLDER)
    }

}