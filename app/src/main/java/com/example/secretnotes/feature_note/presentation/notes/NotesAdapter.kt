package com.example.secretnotes.feature_note.presentation.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secretnotes.databinding.ItemNoteBinding
import com.example.secretnotes.feature_note.domain.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter(val noteList: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private lateinit var mListener: onItemClickListener

    inner class NoteViewHolder(val binding: ItemNoteBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(get: Note) {
            binding.textViewDate.text = convertLongToDate(get.timestamp)
            binding.textViewTitle.text = get.title
            if (get.isEdited)
                binding.textViewNoteIsEdited.visibility = View.VISIBLE
            binding.textViewNoteSummary.text = get.content
        }

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindItems(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    private fun convertLongToDate(time: Long): String {

        val date = Date(time)
        val format = SimpleDateFormat(
            "dd/M/yyyy hh:mm", Locale.getDefault()
        )
        return format.format(date)
    }
}