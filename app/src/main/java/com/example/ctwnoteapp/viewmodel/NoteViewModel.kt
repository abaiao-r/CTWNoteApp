// app/src/main/java/com/example/ctwnoteapp/viewmodel/NoteViewModel.kt
package com.example.ctwnoteapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.ctwnoteapp.model.Note
import com.example.ctwnoteapp.model.SortOption
import java.util.Date

class NoteViewModel : ViewModel() {
    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var notes by mutableStateOf<List<Note>>(emptyList())
    var sortOption by mutableStateOf(SortOption.NEWEST)
    var editId by mutableStateOf<String?>(null)
    var showClearDialog by mutableStateOf(false)
    var expanded by mutableStateOf(false)
    var noteToDelete by mutableStateOf<Note?>(null)

    fun saveNote(context: Context) {
        if (title.isNotEmpty() && content.isNotEmpty()) {
            if (editId == null) {
                notes = notes + Note(
                    title = title,
                    content = content
                )
            } else {
                notes = notes.map { note ->
                    if (note.id == editId) note.copy(title = title, content = content, updatedDate = Date())
                    else note
                }
                editId = null
            }
            title = ""
            content = ""
        } else {
            Toast.makeText(context, "Both fields must be filled!", Toast.LENGTH_SHORT).show()
        }
    }

    fun clearFields() {
        title = ""
        content = ""
        showClearDialog = false
    }

    fun deleteNote() {
        noteToDelete?.let { note ->
            notes = notes.filterNot { it.id == note.id }
            noteToDelete = null
        }
    }

    fun sortNotes(): List<Note> {
        return when (sortOption) {
            SortOption.NEWEST -> notes.sortedByDescending { it.createdDate }
            SortOption.OLDEST -> notes.sortedBy { it.createdDate }
            SortOption.ALPHABETICAL -> notes.sortedBy { it.title }
        }
    }

    fun permanentlyDeleteNote(note: Note) {
        notes = notes.filterNot { it.id == note.id }
    }

    fun markNoteAsDeleted(note: Note) {
        notes = notes.map {
            if (it.id == note.id) it.copy(isDeleted = true) else it
        }
    }

    fun recoverNote(note: Note) {
        notes = notes.map {
            if (it.id == note.id) it.copy(isDeleted = false) else it
        }
    }

    fun getActiveNotes(): List<Note> {
        return notes.filter { !it.isDeleted }
    }
}