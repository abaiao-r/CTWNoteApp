// NoteInputScreen.kt
package com.example.ctwnoteapp.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme
import com.example.ctwnoteapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInputScreen(navController: NavHostController, viewModel: NoteViewModel, context: Context) {
    Column(modifier = Modifier.fillMaxSize()) {
        Banner()
        TopAppBar(
            title = { Text("Edit Note") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()) {
            NoteInputFields(
                title = viewModel.title,
                onTitleChange = { viewModel.title = it },
                content = viewModel.content,
                onContentChange = { viewModel.content = it }
            )
            NoteActionButtons(
                title = viewModel.title,
                content = viewModel.content,
                editId = viewModel.editId,
                onSaveNote = {
                    viewModel.saveNote(context)
                    navController.navigateUp()
                },
                onShowClearDialog = { viewModel.showClearDialog = true },
                buttonColor = MaterialTheme.colorScheme.primary,
                buttonBorderColor = MaterialTheme.colorScheme.onPrimary,
                clearButtonColor = MaterialTheme.colorScheme.secondary
            )
            if (viewModel.showClearDialog) {
                ClearConfirmationDialog(
                    onClear = { viewModel.clearFields() },
                    onDismiss = { viewModel.showClearDialog = false }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteInputScreenPreview() {
    CTWNoteAppTheme {
        NoteInputScreen(
            navController = NavHostController(LocalContext.current),
            viewModel = NoteViewModel(),
            context = LocalContext.current
        )
    }
}
