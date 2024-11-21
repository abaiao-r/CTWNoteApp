// NoteDao.kt
package com.example.ctwnoteapp.model

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes WHERE isDeleted = 0")
    fun getActiveNotes(): List<Note>

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE isDeleted = 1")
    fun getDeletedNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM notes WHERE id = :noteId")
    fun permanentlyDeleteNoteById(noteId: String)

    @Query("UPDATE notes SET isDeleted = 1 WHERE id = :noteId")
    fun markNoteAsDeleted(noteId: String)

    @Query("UPDATE notes SET isDeleted = 0 WHERE id = :noteId")
    fun recoverNoteById(noteId: String)
}