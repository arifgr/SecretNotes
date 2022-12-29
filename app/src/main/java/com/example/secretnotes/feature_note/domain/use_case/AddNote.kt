package com.example.secretnotes.feature_note.domain.use_case

import com.example.secretnotes.feature_note.domain.model.Note
import com.example.secretnotes.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}