package com.android.diarystud.database.room.repository

import androidx.lifecycle.LiveData
import com.android.diarystud.database.DatabaseRepository
import com.android.diarystud.database.room.dao.FolderRoomDao
import com.android.diarystud.database.room.dao.NoteRoomDao
import com.android.diarystud.model.Folder
import com.android.diarystud.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao,
                     private val folderRoomDao: FolderRoomDao): DatabaseRepository {
    override val readAllNotes: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun createNote(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
        onSuccess()
    }

    override suspend fun updateNote(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun deleteNote(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
        onSuccess()
    }

    // Далее пойдут папки

    override val readAllFolders: LiveData<List<Folder>>
        get() = folderRoomDao.getAllFolders()

    override val readLastFolderId: Int
        get() = folderRoomDao.getLastFolderId()

    override suspend fun getFolderById(id: Int, onSuccess: (Folder) -> Unit) {
        val folder = folderRoomDao.getFolderById(id = id)
        onSuccess(folder)
    }

    override suspend fun createFolder(folder: Folder, onSuccess: () -> Unit) {
        folderRoomDao.addFolder(folder = folder)
        onSuccess()
    }

    override suspend fun updateFolder(folder: Folder, onSuccess: () -> Unit) {
        folderRoomDao.updateFolder(folder = folder)
        onSuccess()
    }

    override suspend fun deleteFolder(folder: Folder, onSuccess: () -> Unit) {
        folderRoomDao.deleteFolder(folder = folder)
        onSuccess()
    }


}