// NoteAddButton.kt
package com.example.ctwnoteapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme

@Composable
fun NoteAddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Note")
    }
}

@Preview(showBackground = true)
@Composable
fun NoteAddButtonPreview() {
    CTWNoteAppTheme {
        NoteAddButton(onClick = {})
    }
}