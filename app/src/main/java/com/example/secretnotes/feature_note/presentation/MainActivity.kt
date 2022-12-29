package com.example.secretnotes.feature_note.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.secretnotes.R
import com.example.secretnotes.feature_note.presentation.add_edit_notes.AddEditNoteViewModel
import com.example.secretnotes.feature_note.presentation.notes.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var notesViewModel: NotesViewModel
    lateinit var addEditNoteViewModel: AddEditNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        addEditNoteViewModel = ViewModelProvider(this)[AddEditNoteViewModel::class.java]
    }
}