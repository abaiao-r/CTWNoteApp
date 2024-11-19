// TrashScreen.kt
package com.example.ctwnoteapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ctwnoteapp.model.Note
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme
import com.example.ctwnoteapp.viewmodel.NoteViewModel

// app/src/main/java/com/example/ctwnoteapp/ui/TrashScreen.kt

@Composable
fun TrashScreen(navController: NavHostController, viewModel: NoteViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Banner()
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Trash",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(viewModel.notes.filter { it.isDeleted }) { note ->
                    NoteCard(
                        title = note.title,
                        content = note.content,
                        color = note.color,
                        createdDate = note.createdDate,
                        updatedDate = note.updatedDate,
                        onEdit = { /* No edit in trash */ },
                        onDelete = { viewModel.permanentlyDeleteNote(note) },
                        onRecover = { viewModel.recoverNote(note) },
                        isInTrash = true
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrashScreenPreview() {
    val navController = rememberNavController()
    val viewModel = NoteViewModel().apply {
        notes = listOf(
            Note(
                title = "Deleted Note 1",
                content = "This is the content of deleted note 1.",
                isDeleted = true
            ),
            Note(
                title = "Deleted Note 2",
                content = "This is the content of deleted note 2.",
                isDeleted = true
            )
        )
    }
    CTWNoteAppTheme {
        TrashScreen(navController = navController, viewModel = viewModel)
    }
}