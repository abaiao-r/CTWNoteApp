package com.example.ctwnoteapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme

@Composable
fun NoteInputFields(
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit
) {
    TextField(
        value = title,
        onValueChange = onTitleChange,
        label = { Text("Title") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        maxLines = 1
    )
    TextField(
        value = content,
        onValueChange = onContentChange,
        label = { Text("Content") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        maxLines = 5
    )
}


@Preview(showBackground = true)
@Composable
fun NoteInputFieldsPreview() {
    CTWNoteAppTheme {
        NoteInputFields(
            title = "Sample Title",
            onTitleChange = {},
            content = "Sample Content",
            onContentChange = {}
        )
    }
}
