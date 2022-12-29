package com.example.secretnotes.feature_note.presentation.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secretnotes.R
import com.example.secretnotes.databinding.FragmentNotesBinding
import com.example.secretnotes.feature_note.domain.model.Note
import com.example.secretnotes.feature_note.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var notesViewModel: NotesViewModel
    lateinit var notesAdapter: NotesAdapter
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).notesViewModel

        setupRecyclerView(listOf())
        notesViewModel.getNotes().observe(viewLifecycleOwner, Observer { notes ->
            setupRecyclerView(notes)
        })

        binding.floatingActionButtonAddNote.setOnClickListener {
            val noteToPass = Note("", "", 0, "", false, null)
            val bundle2 = Bundle().apply {
                putSerializable("note", noteToPass)
            }
            findNavController().navigate(R.id.action_notesFragment_to_addEditNoteFragment, bundle2)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(notes: List<Note>) {
        notesAdapter = NotesAdapter(notes)
        binding.recyclerViewNotes.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        notesAdapter.setOnItemClickListener(object : NotesAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val bundle = Bundle().apply {
                    putSerializable("note", notes[position])
                }
                findNavController().navigate(R.id.action_notesFragment_to_addEditNoteFragment, bundle)
            }

        })
    }
}