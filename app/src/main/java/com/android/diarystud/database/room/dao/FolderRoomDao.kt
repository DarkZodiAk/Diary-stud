package com.android.diarystud.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.diarystud.model.Folder


@Dao
interface FolderRoomDao {

    @Query("SELECT * FROM folders_table")
    fun getAllFolders(): LiveData<List<Folder>>

    @Query("SELECT id FROM folders_table ORDER BY id DESC LIMIT 1")
    fun getLastFolderId(): Int

    @Query("SELECT * FROM folders_table WHERE id = :id")
    fun getFolderById(id: Int): Folder

    @Insert
    suspend fun addFolder(folder: Folder)

    @Update
    suspend fun updateFolder(folder: Folder)

    @Delete
    suspend fun deleteFolder(folder: Folder)
}