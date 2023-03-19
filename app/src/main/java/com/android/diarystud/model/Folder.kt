package com.android.diarystud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.diarystud.utils.Constants.Keys.FOLDERS_TABLE

@Entity(tableName = FOLDERS_TABLE)
data class Folder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String
)
