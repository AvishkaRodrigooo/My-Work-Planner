package com.example.notessqli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqli.databinding.ActivityMainBinding
import com.example.notessqli.databinding.ActivityUpdateactivityBinding

class updateactivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateactivityBinding
    private lateinit var db : NotesDatabaseHelper
    private  var noteId : Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.UpdateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
             val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.UpdateContentEditText.text.toString()
            val updatedNote = Note(noteId, newTitle, newContent)

            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Update Successfull!",Toast.LENGTH_SHORT).show()

        }

    }
}