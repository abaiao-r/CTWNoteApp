// NoteActionButtons.kt
package com.example.ctwnoteapp.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwnoteapp.model.Note
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme
import java.util.Date

@Composable
fun NoteActionButtons(
    title: String,
    content: String,
    editId: String?,
    notes: List<Note>,
    onSaveNote: (List<Note>, String?) -> Unit,
    onShowClearDialog: () -> Unit,
    buttonColor: Color,
    buttonBorderColor: Color,
    clearButtonColor: Color,
    context: android.content.Context
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Button(
            onClick = {
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    val newNotes = if (editId == null) {
                        notes + Note(
                            title = title,
                            content = content
                        )
                    } else {
                        notes.map { note ->
                            if (note.id == editId) note.copy(title = title, content = content, updatedDate = Date())
                            else note
                        }
                    }
                    onSaveNote(newNotes, null)
                } else {
                    Toast.makeText(context, "Both fields must be filled!", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            border = BorderStroke(1.dp, buttonBorderColor),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.size(150.dp, 48.dp)
        ) {
            Text(if (editId == null) "Save note" else "Update note")
        }
        Button(
            onClick = onShowClearDialog,
            colors = ButtonDefaults.buttonColors(containerColor = clearButtonColor),
            border = BorderStroke(1.dp, buttonBorderColor),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.size(150.dp, 48.dp)
        ) {
            Text("Clear")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteActionButtonsPreview() {
    CTWNoteAppTheme {
        NoteActionButtons(
            title = "Sample Title",
            content = "Sample Content",
            editId = null,
            notes = emptyList(),
            onSaveNote = { _, _ -> },
            onShowClearDialog = {},
            buttonColor = MaterialTheme.colorScheme.primary,
            buttonBorderColor = MaterialTheme.colorScheme.onPrimary,
            clearButtonColor = MaterialTheme.colorScheme.secondary,
            context = LocalContext.current
        )
    }
}