package com.android.diarystud.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.diarystud.MainViewModel
import com.android.diarystud.screens.*
import com.android.diarystud.screens.elements.utils.Constants


sealed class NavRoute(val route: String){
    object Start: NavRoute(Constants.Screens.START_SCREEN);
    object Diary: NavRoute(Constants.Screens.DIARY_SCREEN);
    object Add: NavRoute(Constants.Screens.ADD_SCREEN);
    object Note: NavRoute(Constants.Screens.NOTE_SCREEN);
    object AddFolder: NavRoute(Constants.Screens.ADD_FOLDER_SCREEN)
    object UpdateFolder: NavRoute(Constants.Screens.UPDATE_FOLDER_SCREEN)
    object Settings: NavRoute(Constants.Screens.SETTINGS_SCREEN);
    object Calendar: NavRoute(Constants.Screens.CALENDAR_SCREEN);
    object Groups: NavRoute(Constants.Screens.GROUPS_SCREEN);
}

@Composable
fun DiaryNavHost(navController: NavHostController, mViewModel: MainViewModel) {

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route){ StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Diary.route + "/{${Constants.Keys.FOLDER_ID}}"){ backStackEntry ->
            DiaryScreen(
                navController = navController,
                viewModel = mViewModel,
                folderId = backStackEntry.arguments?.getString(Constants.Keys.FOLDER_ID)
            )
        }
        composable(NavRoute.AddFolder.route + "/{${Constants.Keys.FOLDER_ID}}"){ backStackEntry ->
            AddFolderScreen(
                navController = navController,
                viewModel = mViewModel,
                folderId = backStackEntry.arguments?.getString(Constants.Keys.FOLDER_ID)
            )
        }
        composable(NavRoute.UpdateFolder.route + "/{${Constants.Keys.FOLDER_ID}}"){ backStackEntry ->
            UpdateFolderScreen(
                navController = navController,
                viewModel = mViewModel,
                folderId = backStackEntry.arguments?.getString(Constants.Keys.FOLDER_ID)
            )
        }

        composable(NavRoute.Add.route + "/{${Constants.Keys.FOLDER_ID}}"){ backStackEntry ->
            AddScreen(
                navController = navController,
                viewModel = mViewModel,
                folderId = backStackEntry.arguments?.getString(Constants.Keys.FOLDER_ID)
            )
        }
        composable(NavRoute.Note.route + "/{${Constants.Keys.FOLDER_ID}}/{${Constants.Keys.NOTE_ID}}") { backStackEntry ->
            NoteScreen(
                navController = navController,
                viewModel = mViewModel,
                folderId = backStackEntry.arguments?.getString(Constants.Keys.FOLDER_ID),
                noteId = backStackEntry.arguments?.getString(Constants.Keys.NOTE_ID)
            )
        }
        composable(route = NavRoute.Settings.route){ SettingsScreen(navController = navController, viewModel = mViewModel) }
        composable(route = NavRoute.Calendar.route){ CalendarScreen(navController = navController, viewModel = mViewModel) }
        composable(route = NavRoute.Groups.route){ GroupsScreen(navController = navController, viewModel = mViewModel) }
    }
} // По причинам представления лучше именовать экраны со Screen в конце