//Andy Scott
//CIS 2818
//Prof John Baugh
//11 December 2023

package com.andyscott.proj4

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andyscott.proj4.databinding.ItemNoteBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val notes = mutableListOf<NoteModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setNotes(notesList: List<NoteModel>) {
        notes.clear()
        notes.addAll(notesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteModel) {
            binding.textViewTitle.text = note.title
            binding.textViewContent.text = note.content
        }
    }
}