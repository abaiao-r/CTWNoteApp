// NoteListScreen.kt
package com.example.ctwnoteapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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


@Composable
fun NoteListScreen(navController: NavHostController, viewModel: NoteViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Banner()
        Column(modifier = Modifier.padding(16.dp).padding(top = 48.dp)) {
            SortButton(
                sortOption = viewModel.sortOption,
                onSortOptionChange = { viewModel.sortOption = it },
                expanded = viewModel.expanded,
                onExpandedChange = { viewModel.expanded = it },
                buttonColor = MaterialTheme.colorScheme.primary,
                buttonBorderColor = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            NoteList(
                notes = viewModel.sortNotes().filter { !it.isDeleted },
                onEditNote = { note ->
                    viewModel.title = note.title
                    viewModel.content = note.content
                    viewModel.editId = note.id
                    navController.navigate("noteInput")
                },
                onDeleteNote = { viewModel.markNoteAsDeleted(it) }
            )
        }
        NoteAddButton(
            onClick = {
                viewModel.title = ""
                viewModel.content = ""
                viewModel.editId = null
                navController.navigate("noteInput")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
        FloatingActionButton(
            onClick = { navController.navigate("trash") },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Trash")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteListScreenPreview() {
    val navController = rememberNavController()
    val viewModel = NoteViewModel().apply {
        notes = listOf(
            Note(
                title = "Sample Note 1",
                content = "This is the content of sample note 1."
            ),
            Note(
                title = "Sample Note 2",
                content = "This is the content of sample note 2."
            )
        )
    }
    CTWNoteAppTheme {
        NoteListScreen(navController = navController, viewModel = viewModel)
    }
}