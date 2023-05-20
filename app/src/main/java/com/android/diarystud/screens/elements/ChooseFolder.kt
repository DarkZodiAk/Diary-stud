package com.android.diarystud.screens.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.diarystud.model.Folder

@Composable
fun ChooseFolder(folders: List<Folder>,
                 onDismiss: () -> Unit,
                 onSuccess: (Folder) -> Unit
) {
    AlertDialog(
        title = {
            Text(text = "Выберите папку", fontSize = 19.sp)
        },
        onDismissRequest = { onDismiss() },
        buttons = {
                LazyColumn(modifier = Modifier.padding(16.dp)){
                items(folders){ folder ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSuccess(folder) }
                        .padding(12.dp)
                    ){
                        Text(folder.name, fontSize = 17.sp)
                    }
                }
            }
        }
    )

}