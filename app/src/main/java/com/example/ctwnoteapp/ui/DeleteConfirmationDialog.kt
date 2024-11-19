package com.example.ctwnoteapp.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwnoteapp.model.Note
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme

@Composable
fun DeleteConfirmationDialog(note: com.example.ctwnoteapp.model.Note, onDelete: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDelete) { Text("Delete") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        title = { Text("Delete Note") },
        text = { Text("Are you sure you want to delete this note?") }
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteConfirmationDialogPreview() {
    CTWNoteAppTheme {
        DeleteConfirmationDialog(
            note = Note(
                title = "Sample Title",
                content = "Sample Content"
            ),
            onDelete = {},
            onDismiss = {}
        )
    }
}