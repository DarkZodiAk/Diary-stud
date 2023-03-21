package com.android.diarystud

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.android.diarystud.database.room.AppRoomDatabase
import com.android.diarystud.database.room.repository.RoomRepository
import com.android.diarystud.model.Folder
import com.android.diarystud.model.Note
import com.android.diarystud.utils.REPOSITORY
import com.android.diarystud.utils.TYPE_FIREBASE
import com.android.diarystud.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel (application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: ()->Unit) {
        Log.d("CheckData", "MainViewModel initDatabase with type: $type")
        when(type){
            TYPE_ROOM -> {
                val noteDao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                val folderDao = AppRoomDatabase.getInstance(context = context).getFolderDao()
                REPOSITORY = RoomRepository(noteDao, folderDao)
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.createNote(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.updateNote(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.deleteNote(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun addFolder(folder: Folder, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.createFolder(folder = folder){
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateFolder(folder: Folder, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.updateFolder(folder = folder){
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteFolder(folder: Folder, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.deleteFolder(folder = folder){
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAllNotes

    fun readAllFolders() = REPOSITORY.readAllFolders

    fun readLastFolderId() = REPOSITORY.readLastFolderId
}


class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}