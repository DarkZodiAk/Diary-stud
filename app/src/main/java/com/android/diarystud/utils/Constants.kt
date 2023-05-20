package com.android.diarystud.utils

import com.android.diarystud.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Constants {
    object Keys {
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_TITLE = "Note title"
        const val NOTE_SUBTITLE = "Note subtitle"
        const val ADD_NOTE = "Add note"
        const val TITLE = "Title"
        const val SUBTITLE = "Subtitle"
        const val WELCOME = "Добро пожаловать!"
        const val AUTH_WITH_GOOGLE = "Войти через Google"
        const val ID = "Id"
        const val NONE = "none"
        const val UPDATE = "UPDATE"
        const val DELETE = "DELETE"
        const val NAV_BACK = "NAV_BACK"
        const val EDIT_NOTE = "Edit note"
        const val EMPTY = ""
        const val UPDATE_NOTE = "Update note"
    }

    object Screens {
        const val START_SCREEN = "start"
        const val DIARY_SCREEN = "diary"
        const val ADD_SCREEN = "add"
        const val NOTE_SCREEN = "note"
        const val SETTINGS_SCREEN = "settings"
        const val CALENDAR_SCREEN = "calendar"
        const val GROUPS_SCREEN = "groups"
    }
}