package com.example.secretnotes.feature_note.data.repository

import com.example.secretnotes.feature_note.data.data_source.NoteDao
import com.example.secretnotes.feature_note.domain.model.Note
import com.example.secretnotes.feature_note.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes() = dao.getNotes()

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}