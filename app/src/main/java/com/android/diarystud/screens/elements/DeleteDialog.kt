package com.android.diarystud.screens.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.diarystud.screens.elements.utils.Constants

@Composable
fun DeleteDialog(onConfirmClick: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        buttons = {
            Row(horizontalArrangement = Arrangement.SpaceBetween){
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text(modifier = Modifier.padding(8.dp), text = "Нет")
                }
                Button(
                    onClick = {
                        onConfirmClick()
                    }) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        color = Color.Red,
                        text = Constants.Keys.DELETE)
                }
            }
        }
    )
}