package com.example.secretnotes.feature_note.domain.repository

import androidx.lifecycle.LiveData
import com.example.secretnotes.feature_note.domain.model.Note

interface NoteRepository {

    fun getNotes(): LiveData<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}