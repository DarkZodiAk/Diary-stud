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
        route = "diary",
        title = "Diary",
        icon = Icons.Default.MailOutline
    )
    object Settings: BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    object Calendar: BottomBarScreen(
        route = "calendar",
        title = "Calendar",
        icon = Icons.Default.Notifications
    )
    object Groups: BottomBarScreen(
        route = "groups",
        title = "Groups",
        icon = Icons.Default.Person
    )
}
