package com.android.diarystud.screens.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.diarystud.screens.elements.utils.Constants

@Composable
fun DeleteDialog(itemTextStyle: TextStyle = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.SemiBold),
                 onConfirmClick: () -> Unit,
                 onDismiss: () -> Unit) {
    AlertDialog(
        title = {
            Text(
                text = "Вы уверены?",
                fontSize = 19.sp
            )
        },
        text = {
            Text(
                text = "Все записи в данной папке будут удалены.",
                fontSize = 17.sp
            )
        },
        onDismissRequest = { onDismiss() },
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth().absolutePadding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                    onClick = { onDismiss() }
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp).padding(horizontal = 16.dp),
                        text = "Нет",
                        color = MaterialTheme.colors.onBackground,
                        style = itemTextStyle
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                    onClick = {
                        onConfirmClick()
                    }) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = Constants.Keys.DELETE,
                        color = Color.Red,
                        style = itemTextStyle
                    )
                }
            }
        }
    )
}