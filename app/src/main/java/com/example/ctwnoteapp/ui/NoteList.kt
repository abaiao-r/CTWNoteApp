// NoteList.kt
package com.example.ctwnoteapp.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwnoteapp.model.Note

@Composable
fun NoteList(
    notes: List<Note>,
    onEditNote: (Note) -> Unit,
    onDeleteNote: (Note) -> Unit
) {
    LazyColumn {
        items(notes) { note ->
            NoteCard(
                title = note.title,
                content = note.content,
                color = note.color,
                createdDate = note.createdDate,
                updatedDate = note.updatedDate,
                onEdit = { onEditNote(note) },
                onDelete = { onDeleteNote(note) }
            )
        }
    }
}

@Preview
@Composable
fun NoteListPreview() {
    val notes = listOf(
        Note(title = "Note 1", content = "Content 1"),
        Note(title = "Note 2", content = "Content 2"),
        Note(title = "Note 3", content = "Content 3")
    )
    NoteList(notes = notes, onEditNote = {}, onDeleteNote = {})

}