package com.android.diarystud.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.android.diarystud.model.Folder
import com.android.diarystud.model.Note
import com.android.diarystud.navigation.NavRoute
import com.android.diarystud.ui.theme.DiaryStudTheme
import com.android.diarystud.utils.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    noteId: String?,
    folderId: String?
    /*title: Int*/
) {
    //Эти 4 строки кода... Надо подумать, а можно ли сделать по-другому?
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val folders = viewModel.readAllFolders().observeAsState(listOf()).value
    val note = notes.firstOrNull{ it.id == noteId?.toInt()} ?: Note(title = Constants.Keys.NONE, subtitle = Constants.Keys.NONE, folder = 0)
    // Вообще по-хорошему надо не !! прописывать, а автоматом выезжать на главную страничку при ошибке
    val folder = folders.firstOrNull { it.id == folderId?.toInt() } ?: Folder(name = Constants.Keys.NONE)

    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY) }


    ModalBottomSheetLayout(
        sheetState =  bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = Constants.Keys.EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)

                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = Constants.Keys.TITLE) },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = { subtitle = it },
                        label = { Text(text = Constants.Keys.SUBTITLE) },
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(note =
                                Note(id = note.id, title = title, subtitle = subtitle, folder = folderId!!.toInt())
                            ) {
                                navController.navigate(NavRoute.Diary.route)
                            }
                        }
                    ) {
                        Text(text = Constants.Keys.UPDATE_NOTE)
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = note.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                        Text(
                            text = note.subtitle,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 16.dp)
                        )

                    }

                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = {
                        coroutineScope.launch {
                            title = note.title
                            subtitle = note.subtitle
                            bottomSheetState.show()
                        }
                    }) {
                        Text(text = Constants.Keys.UPDATE)
                    }
                    Button(onClick = {
                        viewModel.deleteNote(note = note) {
                            navController.navigate(NavRoute.Diary.route)
                        }
                    }) {
                        Text(text = Constants.Keys.DELETE)
                    }

                }
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    onClick = {
                        navController.navigate(NavRoute.Diary.route)
                    }
                ) {
                    Text(text = Constants.Keys.NAV_BACK)
                }

            }

        }
    }


}


@Preview(showBackground = true)
@Composable
fun prevNoteScreen() {
    DiaryStudTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        NoteScreen (
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1",
            folderId = "0"
        )
    }
}
