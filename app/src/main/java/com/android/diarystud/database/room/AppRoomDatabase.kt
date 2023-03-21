package com.android.diarystud.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.diarystud.database.room.dao.FolderRoomDao
import com.android.diarystud.database.room.dao.NoteRoomDao
import com.android.diarystud.model.Folder
import com.android.diarystud.model.Note
import com.android.diarystud.utils.Constants.Keys.FOLDERS_TABLE
import com.android.diarystud.utils.Constants.Keys.NOTES_TABLE
import com.android.diarystud.utils.Constants.Keys.NOTE_DATABASE

//version - это просто число. Когда структура БД (столбцы, таблицы и т.д.) изменяется, надо увеличить версию на 1
//НИ В КОЕМ СЛУЧАЕ НЕ ИСПОЛЬЗОВАТЬ .fallbackToDestructiveMigration(). Надо писать SQL-запросы.
@Database(entities = [Note::class, Folder::class], version = 2, exportSchema = true)
abstract class AppRoomDatabase: RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao
    abstract fun getFolderDao(): FolderRoomDao


    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        val migration1To2 = object: Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `$FOLDERS_TABLE` " +
                        "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)")
                database.execSQL("DROP TABLE $NOTES_TABLE")
                database.execSQL("CREATE TABLE IF NOT EXISTS `$NOTES_TABLE` " +
                        "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`title` TEXT NOT NULL, `subtitle` TEXT NOT NULL, " +
                        "`folder_id` INTEGER NOT NULL DEFAULT 0, " +
                        "FOREIGN KEY(`folder_id`) REFERENCES `folders_table`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
            }
        }

        fun getInstance(context: Context): AppRoomDatabase{
            return if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    NOTE_DATABASE
                ).createFromAsset("database/default.db").build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }
    }
}