package com.example.ctwnoteapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import kotlin.random.Random

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val createdDate: Date = Date(),
    var updatedDate: Date = Date(),
    val color: Color = generateRandomColor()
)

enum class SortOption(val displayName: String) {
    NEWEST("Newest"),
    OLDEST("Oldest"),
    ALPHABETICAL("A - Z")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CTWNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NoteApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NoteApp(
    modifier: Modifier = Modifier,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    buttonBorderColor: Color = MaterialTheme.colorScheme.onPrimary,
    clearButtonColor: Color = MaterialTheme.colorScheme.secondary
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf<List<Note>>(emptyList()) }
    var sortOption by remember { mutableStateOf(SortOption.NEWEST) }
    var editId by remember { mutableStateOf<String?>(null) } // Track the ID of the note being edited
    var showClearDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var noteToDelete by remember { mutableStateOf<Note?>(null) }

    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {
        // Banner
        Text(
            text = "CTW Note App by André Francisco",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .padding(8.dp)
        )

        Column(modifier = Modifier.padding(16.dp).padding(top = 48.dp)) {
            // Title and content fields
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                maxLines = 1 // Limit to one line for the title
            )

            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                maxLines = 5 // Limit to five lines for the content
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = {
                        if (title.isNotEmpty() && content.isNotEmpty()) {
                            if (editId == null) {
                                // Add a new note
                                notes = notes + Note(
                                    title = title,
                                    content = content
                                )
                            } else {
                                // Update the existing note
                                notes = notes.map { note ->
                                    if (note.id == editId) note.copy(title = title, content = content, updatedDate = Date())
                                    else note
                                }
                                editId = null // Reset the editId
                            }
                            title = ""
                            content = ""
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
                    onClick = { showClearDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = clearButtonColor),
                    border = BorderStroke(1.dp, buttonBorderColor),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.size(150.dp, 48.dp)
                ) {
                    Text("Clear")
                }
            }

            // Clear Confirmation Dialog
            if (showClearDialog) {
                AlertDialog(
                    onDismissRequest = { showClearDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            title = ""
                            content = ""
                            showClearDialog = false
                        }) { Text("Clear") }
                    },
                    dismissButton = {
                        TextButton(onClick = { showClearDialog = false }) { Text("Cancel") }
                    },
                    title = { Text("Clear Fields") },
                    text = { Text("Are you sure you want to clear the fields?") }
                )
            }

            // Delete Confirmation Dialog
            noteToDelete?.let { note ->
                AlertDialog(
                    onDismissRequest = { noteToDelete = null },
                    confirmButton = {
                        TextButton(onClick = {
                            notes = notes.filterNot { it.id == note.id }
                            noteToDelete = null
                            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                        }) { Text("Delete") }
                    },
                    dismissButton = {
                        TextButton(onClick = { noteToDelete = null }) { Text("Cancel") }
                    },
                    title = { Text("Delete Note") },
                    text = { Text("Are you sure you want to delete this note?") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box {
                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    border = BorderStroke(1.dp, buttonBorderColor),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.size(150.dp, 48.dp)
                ) {
                    Text("Sort by: ${sortOption.displayName}", maxLines = 1, overflow = TextOverflow.Ellipsis)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    SortOption.values().forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                sortOption = option
                                expanded = false
                            },
                            text = { Text(option.displayName) }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Display sorted notes with delete and edit functionality
            val sortedNotes = when (sortOption) {
                SortOption.NEWEST -> notes.sortedByDescending { it.createdDate }
                SortOption.OLDEST -> notes.sortedBy { it.createdDate }
                SortOption.ALPHABETICAL -> notes.sortedBy { it.title }
            }

            LazyColumn {
                items(sortedNotes) { note ->
                    NoteCard(
                        title = note.title,
                        content = note.content,
                        color = note.color,
                        createdDate = note.createdDate,
                        updatedDate = note.updatedDate,
                        onEdit = {
                            title = note.title
                            content = note.content
                            editId = note.id // Set editId to the note's unique ID
                        },
                        onDelete = {
                            noteToDelete = note
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    color: Color,
    createdDate: Date,
    updatedDate: Date,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val textColor = if (color.luminance() > 0.5) Color.Black else Color.White
    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) }

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium.copy(color = textColor))
            if (expanded) {
                Text(text = content, style = MaterialTheme.typography.bodyMedium.copy(color = textColor), modifier = Modifier.padding(bottom = 8.dp))
                Text(text = "Created: ${dateFormatter.format(createdDate)}", style = MaterialTheme.typography.bodySmall.copy(color = textColor))
                Text(text = "Updated: ${dateFormatter.format(updatedDate)}", style = MaterialTheme.typography.bodySmall.copy(color = textColor))
            } else {
                Text(text = content.lineSequence().firstOrNull() ?: "", style = MaterialTheme.typography.bodyMedium.copy(color = textColor), maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(bottom = 8.dp))
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit note", tint = textColor)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete note", tint = textColor)
                }
            }
        }
    }
}

// Function to generate a random color
fun generateRandomColor(): Color {
    return Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
}

@Preview(showBackground = true)
@Composable
fun NoteAppPreview() {
    CTWNoteAppTheme {
        NoteApp()
    }
}
