// NoteRepository.kt
package com.example.ctwnoteapp.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun getActiveNotes(): List<Note> = withContext(Dispatchers.IO) {
        noteDao.getActiveNotes()
    }

    suspend fun getAllNotes(): List<Note> = withContext(Dispatchers.IO) {
        noteDao.getAllNotes()
    }


    suspend fun insert(note: Note) = withContext(Dispatchers.IO) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) = withContext(Dispatchers.IO) {
        noteDao.update(note)
    }

    suspend fun markNoteAsDeleted(noteId: String) = withContext(Dispatchers.IO) {
        noteDao.markNoteAsDeleted(noteId)
    }

    suspend fun permanentlyDeleteNoteById(noteId: String) = withContext(Dispatchers.IO) {
        noteDao.permanentlyDeleteNoteById(noteId)
    }

    suspend fun recoverNoteById(noteId: String) = withContext(Dispatchers.IO) {
        noteDao.recoverNoteById(noteId)
    }
}