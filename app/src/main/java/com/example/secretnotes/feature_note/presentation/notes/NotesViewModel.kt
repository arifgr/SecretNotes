package com.example.secretnotes.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import com.example.secretnotes.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    fun getNotes() = noteUseCases.getNotes.invoke()
}