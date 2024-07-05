//Andy Scott
//CIS 2818
//Prof John Baugh
//11 December 2023

package com.andyscott.proj4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andyscott.proj4.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var dbHelper: NoteDbHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = NoteDbHelper(this)

        // Initialize RecyclerView
        notesAdapter = NotesAdapter()
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNotes.adapter = notesAdapter

        // Load notes from the database and update the adapter
        val notes = getAllNotes()
        notesAdapter.setNotes(notes)
    }

    private fun getAllNotes(): List<NoteModel> {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            NoteContract.NoteEntry.COLUMN_TITLE,
            NoteContract.NoteEntry.COLUMN_CONTENT
        )

        val cursor = db.query(
            NoteContract.NoteEntry.TABLE_NAME,
            projection, null, null, null, null, null
        )

        val notes = mutableListOf<NoteModel>()
        with(cursor) {
            while (moveToNext()) {
                val title = getString(getColumnIndexOrThrow(NoteContract.NoteEntry.COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(NoteContract.NoteEntry.COLUMN_CONTENT))
                notes.add(NoteModel(title, content))
            }
        }

        return notes

    }
}