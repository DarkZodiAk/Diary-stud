package com.android.diarystud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.diarystud.screens.AddScreen
import com.android.diarystud.screens.DiaryScreen
import com.android.diarystud.screens.NoteScreen
import com.android.diarystud.screens.StartScreen


sealed class NavRoute(val route: String){
    object Start: NavRoute("start_screen");
    object Diary: NavRoute("diary_screen");
    object Add: NavRoute("add_screen");
    object Note: NavRoute("note_screen");
}

@Composable
fun DiaryNavHost() {
    val navController =  rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route){ StartScreen(navController = navController) }
        composable(NavRoute.Diary.route){ DiaryScreen(navController = navController) }
        composable(NavRoute.Add.route){ AddScreen(navController = navController) }
        composable(NavRoute.Note.route){ NoteScreen(navController = navController) }
    }
} // По причинам представления лучше именовать экраны со Screen в конце