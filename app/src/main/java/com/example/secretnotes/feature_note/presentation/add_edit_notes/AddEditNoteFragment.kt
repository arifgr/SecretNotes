package com.example.secretnotes.feature_note.presentation.add_edit_notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.secretnotes.R
import com.example.secretnotes.databinding.FragmentAddEditNoteBinding
import com.example.secretnotes.feature_note.domain.model.Note
import com.example.secretnotes.feature_note.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditNoteFragment : Fragment(R.layout.fragment_add_edit_note) {

    lateinit var addEditNoteViewModel: AddEditNoteViewModel
    lateinit var noteNew: Note
    private var _binding: FragmentAddEditNoteBinding? = null
    private val binding get() = _binding!!
    val args:  AddEditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addEditNoteViewModel = (activity as MainActivity).addEditNoteViewModel
        val note = args.note
        noteNew = note.copy()
        binding.editTextTitle.setText(noteNew.title)
        binding.editTextContent.setText(noteNew.content)
        if (noteNew.isEdited)
            binding.textViewIsEdited.visibility = View.VISIBLE
        binding.floatingActionButtonSaveNote.setOnClickListener {
            noteNew.title = binding.editTextTitle.text.toString()
            noteNew.content = binding.editTextContent.text.toString()
            noteNew.timestamp = System.currentTimeMillis()
            noteNew.imageUrl = binding.editTextContent.text.toString()

            if (noteNew.title.isBlank() || noteNew.content.isBlank())
                Toast.makeText(activity, "Title or content cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            else {
                if (noteNew.id != null) {
                    noteNew.isEdited = true
                    binding.textViewIsEdited.visibility = View.VISIBLE
                }
                addEditNoteViewModel.saveEditNote(noteNew)
                Toast.makeText(activity, "The note has been saved", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addEditNoteFragment_to_notesFragment)
            }
        }
        with(binding.floatingActionButtonDeleteNote) {
            if (noteNew.id != null) {
                visibility = View.VISIBLE
            }
            setOnClickListener {
                addEditNoteViewModel.deleteNote(noteNew)
                Toast.makeText(activity, "The note has been deleted", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addEditNoteFragment_to_notesFragment)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.editTextTitle.clearFocus()
        binding.editTextContent.clearFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}