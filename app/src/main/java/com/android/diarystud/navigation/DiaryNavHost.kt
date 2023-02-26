package com.android.diarystud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.diarystud.screens.Add
import com.android.diarystud.screens.Diary
import com.android.diarystud.screens.Note
import com.android.diarystud.screens.Start


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
        composable(NavRoute.Start.route){ Start(navController = navController) }
        composable(NavRoute.Diary.route){ Diary(navController = navController) }
        composable(NavRoute.Add.route){ Add(navController = navController) }
        composable(NavRoute.Note.route){ Note(navController = navController) }
    }
}