package com.android.diarystud

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.diarystud.navigation.DiaryNavHost
import com.android.diarystud.screens.elements.utils.Constants
import com.android.diarystud.ui.theme.DiaryStudTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaryStudTheme {
                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                // bottomBar
                Scaffold(
                    bottomBar = { if (showBottomBar(navBackStackEntry?.destination?.route)) BottomBar(navController = navController) },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize().padding(it),
                            color = MaterialTheme.colors.background
                        ) {
                            DiaryNavHost(navController, mViewModel)
                        }
                    }
                )
            }
        }
    }
}

fun showBottomBar(route: String?): Boolean{
    if (route == null){
        return false
    }
    if (route.startsWith(Constants.Screens.SETTINGS_SCREEN)){ return true }
    if (route.startsWith(Constants.Screens.DIARY_SCREEN)){ return true }
    if (route.startsWith(Constants.Screens.GROUPS_SCREEN)){ return true }
    if (route.startsWith(Constants.Screens.CALENDAR_SCREEN)){ return true }
    return false
}


// bottomNav
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Diary,
        BottomBarScreen.Settings,
        BottomBarScreen.Calendar,
        BottomBarScreen.Groups
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}



@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(route = screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
// bottomNav


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiaryStudTheme {
    }
}