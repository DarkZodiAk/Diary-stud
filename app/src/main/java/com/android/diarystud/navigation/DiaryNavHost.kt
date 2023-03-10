package com.android.diarystud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.diarystud.MainViewModel
import com.android.diarystud.screens.AddScreen
import com.android.diarystud.screens.DiaryScreen
import com.android.diarystud.screens.NoteScreen
import com.android.diarystud.screens.StartScreen
import com.android.diarystud.utils.Constants


sealed class NavRoute(val route: String){
    object Start: NavRoute(Constants.Screens.START_SCREEN);
    object Diary: NavRoute(Constants.Screens.DIARY_SCREEN);
    object Add: NavRoute(Constants.Screens.ADD_SCREEN);
    object Note: NavRoute(Constants.Screens.NOTE_SCREEN);
}

@Composable
fun DiaryNavHost(mViewModel: MainViewModel) {
    val navController =  rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route){ StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Diary.route){ DiaryScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route){ AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            NoteScreen(
                navController = navController,
                viewModel = mViewModel,
                noteId = backStackEntry.arguments?.getString(Constants.Keys.ID),
            )
        }
    }
} // По причинам представления лучше именовать экраны со Screen в конце