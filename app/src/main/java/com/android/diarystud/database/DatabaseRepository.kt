package com.android.diarystud.database

import androidx.lifecycle.LiveData
import com.android.diarystud.model.Folder
import com.android.diarystud.model.Note

interface DatabaseRepository {
    val readAllNotes: LiveData<List<Note>>

    suspend fun createNote(note: Note, onSuccess: ()-> Unit)

    suspend fun updateNote(note: Note, onSuccess: ()-> Unit)

    suspend fun deleteNote(note: Note, onSuccess: ()-> Unit)


    val readAllFolders: LiveData<List<Folder>>

    val readLastFolderId: Int

    suspend fun createFolder(folder: Folder, onSuccess: ()-> Unit)

    suspend fun updateFolder(folder: Folder, onSuccess: ()-> Unit)

    suspend fun deleteFolder(folder: Folder, onSuccess: ()-> Unit)
}