package com.android.diarystud.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.diarystud.model.Note


@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}

//TODO("Здесь можно потом сортировку бахнуть")