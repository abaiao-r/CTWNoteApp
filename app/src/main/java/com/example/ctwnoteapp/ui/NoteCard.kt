package com.example.ctwnoteapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    CTWNoteAppTheme {
        NoteCard(
            title = "Sample Title",
            content = "Sample Content",
            color = Color.Blue,
            createdDate = Date(),
            updatedDate = Date(),
            onEdit = {},
            onDelete = {}
        )
    }
}