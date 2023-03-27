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
        const val FOLDERS_TABLE = "folders_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_TITLE = "Note title"
        const val NOTE_SUBTITLE = "Note subtitle"
        const val ADD_NOTE = "Add note"
        const val TITLE = "Title"
        const val SUBTITLE = "Subtitle"
        const val WELCOME = "Добро пожаловать!"
        const val AUTH_WITH_GOOGLE = "Войти через Google"
        const val ADD_FOLDER = "Создать папку"
        const val UPDATE_FOLDER = "Изменить папку"
        const val FOLDER_TITLE = "Название папки"
        const val DEFAULT_FOLDER_NAME = "Сегодня"
        const val NOTE_ID = "note_id"
        const val FOLDER_ID = "folder_id"
        const val NONE = "none"
        const val UPDATE = "UPDATE"
        const val DELETE = "DELETE"
        const val NAV_BACK = "NAV_BACK"
        const val EDIT_NOTE = "Edit note"
        const val EMPTY = ""
        const val UPDATE_NOTE = "Update note"
    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val DIARY_SCREEN = "diary_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
        const val ADD_FOLDER_SCREEN = "add_folder_screen"
        const val UPDATE_FOLDER_SCREEN = "update_folder_screen"
    }
}