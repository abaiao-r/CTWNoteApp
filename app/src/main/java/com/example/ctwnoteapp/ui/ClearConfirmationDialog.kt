package com.example.ctwnoteapp.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme

@Composable
fun ClearConfirmationDialog(onClear: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onClear) { Text("Clear") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        title = { Text("Clear Fields") },
        text = { Text("Are you sure you want to clear the fields?") }
    )
}

@Preview(showBackground = true)
@Composable
fun ClearConfirmationDialogPreview() {
    CTWNoteAppTheme {
        ClearConfirmationDialog(
            onClear = {},
            onDismiss = {}
        )
    }
}