package com.example.secretnotes.feature_note.presentation.add_edit_notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretnotes.feature_note.domain.model.Note
import com.example.secretnotes.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    fun saveEditNote(note: Note) = viewModelScope.launch {
        noteUseCases.addNote.invoke(note)
    }


    fun deleteNote(note: Note) = viewModelScope.launch {
        noteUseCases.deleteNote.invoke(note)
    }
}