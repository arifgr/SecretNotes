package com.example.secretnotes.feature_note.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.secretnotes.feature_note.domain.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}