package com.android.diarystud

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Diary: BottomBarScreen(
        route = "diary_screen/0",
        title = "Diary",
        icon = Icons.Default.MailOutline
    )
    object Settings: BottomBarScreen(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    object Calendar: BottomBarScreen(
        route = "calendar_screen",
        title = "Calendar",
        icon = Icons.Default.Notifications
    )
    object Groups: BottomBarScreen(
        route = "groups_screen",
        title = "Groups",
        icon = Icons.Default.Person
    )
}