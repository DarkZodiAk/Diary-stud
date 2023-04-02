package com.android.diarystud.screens.elements

import android.view.GestureDetector
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.diarystud.model.Folder
import com.android.diarystud.screens.elements.utils.Constants.Keys.ADD_FOLDER

/*@Composable
fun FolderRow(
    folder: Folder,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit
){
    var folderButtonEnabled by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(if (isSelected) Color.LightGray else Color.White)
            .pointerInput(Unit){
                detectTapGestures(
                    onTap = { onItemClick() },
                    onLongPress = { folderButtonEnabled = true }
                )
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = folder.name,
            style = itemTextStyle
        )
        if (isSelected) {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
        }
    }
}*/


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrawerBody(
    folders: List<Folder>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    scaffoldState: ScaffoldState,
    onItemClick: (Folder) -> Unit,
    onUpdateClick: (Folder) -> Unit,
    onDeleteClick: (Folder) -> Unit
) {

    var selectedRowIndex by remember { mutableStateOf(-1) }
    
    LaunchedEffect(key1 = scaffoldState.drawerState.isOpen){
        if (!scaffoldState.drawerState.isOpen){
            selectedRowIndex = -1
        }
    } /*TODO("Это что еще за прикол такой, а? Надо в этих эффектах разобраться")*/
    
    LazyColumn(
        modifier
            .fillMaxSize()
            .pointerInput(Unit) { detectTapGestures(onTap = { selectedRowIndex = -1 }) }
    ){
        itemsIndexed(folders){ index, folder ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(if (selectedRowIndex == index) Color(215, 215, 215) else Color.White)
                .combinedClickable(
                    onClick = {
                        selectedRowIndex = -1
                        onItemClick(folder)
                    },
                    onLongClick = { selectedRowIndex = index }
                ),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = folder.name,
                    style = itemTextStyle
                )
                if (selectedRowIndex == index && index != 0) {
                    Row(modifier = Modifier.padding(4.dp)) {
                        IconButton(onClick = { onDeleteClick(folder) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        }
                        IconButton(onClick = { onUpdateClick(folder) }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        }
                    }
                }
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