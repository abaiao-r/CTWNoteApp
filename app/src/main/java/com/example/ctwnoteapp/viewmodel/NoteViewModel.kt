// NoteViewModel.kt
package com.example.ctwnoteapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.identity.util.UUID
import com.example.ctwnoteapp.model.Note
import com.example.ctwnoteapp.model.NoteRepository
import com.example.ctwnoteapp.model.SortOption
import kotlinx.coroutines.launch
import java.util.Date

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var notes by mutableStateOf<List<Note>>(emptyList())
    var sortOption by mutableStateOf(SortOption.NEWEST)
    var editId by mutableStateOf<String?>(null)
    var showClearDialog by mutableStateOf(false)
    var expanded by mutableStateOf(false)
    var noteToDelete by mutableStateOf<Note?>(null)

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            notes = repository.getAllNotes()
        }
    }

    fun saveNote(context: Context) {
        viewModelScope.launch {
            if (title.isNotEmpty() && content.isNotEmpty()) {
                val note = Note(
                    id = editId ?: UUID.randomUUID().toString(),
                    title = title,
                    content = content,
                    createdDate = if (editId == null) Date() else notes.find { it.id == editId }?.createdDate ?: Date(),
                    updatedDate = Date()
                )
                if (editId == null) {
                    repository.insert(note)
                } else {
                    repository.update(note)
                }
                loadNotes()
                clearFields()
                Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Title and content cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun clearFields() {
        title = ""
        content = ""
        showClearDialog = false
    }

    fun deleteNote() {
        viewModelScope.launch {
            noteToDelete?.let { note ->
                repository.markNoteAsDeleted(note.id)
                loadNotes()
                noteToDelete = null
            }
        }
    }

    fun permanentlyDeleteNote(note: Note) {
        viewModelScope.launch {
            repository.permanentlyDeleteNoteById(note.id)
            loadNotes()
        }
    }

    fun recoverNote(note: Note) {
        viewModelScope.launch {
            repository.recoverNoteById(note.id)
            loadNotes()
        }
    }

    fun sortNotes(): List<Note> {
        return when (sortOption) {
            SortOption.NEWEST -> notes.sortedByDescending { it.createdDate }
            SortOption.OLDEST -> notes.sortedBy { it.createdDate }
            SortOption.ALPHABETICAL -> notes.sortedBy { it.title }
        }
    }

    fun getActiveNotes(): List<Note> {
        return notes.filter { !it.isDeleted }
    }

    fun markNoteAsDeleted(note: Note) {
        viewModelScope.launch {
            repository.markNoteAsDeleted(note.id)
            loadNotes()
        }
    }
}