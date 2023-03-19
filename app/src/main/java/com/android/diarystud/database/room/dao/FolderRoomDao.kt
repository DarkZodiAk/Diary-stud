package com.android.diarystud.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.diarystud.model.Folder


@Dao
interface FolderRoomDao {

    @Query("SELECT * FROM folders_table")
    fun getAllFolders(): LiveData<List<Folder>>

    @Insert
    suspend fun addFolder(folder: Folder)

    @Update
    suspend fun updateFolder(folder: Folder)

    @Delete
    suspend fun deleteFolder(folder: Folder)
}