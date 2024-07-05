//Andy Scott
//CIS 2818
//Prof John Baugh
//11 December 2023

package com.andyscott.proj4

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andyscott.proj4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var dbHelper: NoteDbHelper

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = NoteDbHelper(this)

        // Initialize RecyclerView widget
        notesAdapter = NotesAdapter()
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNotes.adapter = notesAdapter

        // Set click listener for the save button to save user's note
        binding.btnSaveNote.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val content = binding.editTextContent.text.toString()
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()

            // Check for empty title or content
            if (title.isNotEmpty() && content.isNotEmpty()) {
                // Save the note to the database
                saveNoteToDb(title, content)

                // Update the adapter with the new data
                notesAdapter.notifyDataSetChanged()

                // Reset the text fields
                binding.editTextTitle.text.clear()
                binding.editTextContent.text.clear()
            }
        }

        // Set click listener for the show notes button to view Notes activity
        binding.btnShowNotes.setOnClickListener {
            Toast.makeText(this, "Now viewing notes", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, NotesActivity::class.java))
        }
    }


    private fun saveNoteToDb(title: String, content: String) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(NoteContract.NoteEntry.COLUMN_TITLE, title)
            put(NoteContract.NoteEntry.COLUMN_CONTENT, content)
        }

        db.insert(NoteContract.NoteEntry.TABLE_NAME, null, values)
    }
}
