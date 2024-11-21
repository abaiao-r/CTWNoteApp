// NoteList.kt
package com.example.ctwnoteapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctwnoteapp.model.Note

@Composable
fun NoteList(
    notes: List<Note>,
    onEditNote: (Note) -> Unit,
    onDeleteNote: (Note) -> Unit
) {
    if (notes.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No notes. Click + to add.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn {
            items(notes) { note ->
                NoteCard(
                    title = note.title,
                    content = note.content,
                    color = Color(note.color), // Convert Int to Color
                    createdDate = note.createdDate,
                    updatedDate = note.updatedDate,
                    onEdit = { onEditNote(note) },
                    onDelete = { onDeleteNote(note) },
                    onRecover = {},
                    isInTrash = false
                )
            }
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

@Preview (showBackground = true)
@Composable
fun NoteListEmptyPreview() {
    NoteList(notes = emptyList(), onEditNote = {}, onDeleteNote = {})
}