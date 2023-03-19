package com.android.diarystud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.android.diarystud.utils.Constants.Keys.NOTES_TABLE

@Entity(tableName = NOTES_TABLE,
    foreignKeys = [ForeignKey(entity = Folder::class, parentColumns = ["id"], childColumns = ["folder_id"], onDelete = CASCADE)]

)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val subtitle: String,

    @ColumnInfo(name = "folder_id", defaultValue = "0")
    val folder: Int

)
