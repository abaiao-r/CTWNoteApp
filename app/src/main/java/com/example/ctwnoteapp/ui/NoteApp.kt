// app/src/main/java/com/example/ctwnoteapp/ui/NoteApp.kt
package com.example.ctwnoteapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme
import com.example.ctwnoteapp.viewmodel.NoteViewModel

@Composable
fun NoteApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: NoteViewModel
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "noteList") {
        composable("noteList") {
            NoteListScreen(navController, viewModel)
        }
        composable("noteInput") {
            NoteInputScreen(navController, viewModel, context)
        }
    }
}



//@Composable
//fun NoteApp(
//    modifier: Modifier = Modifier,
//    viewModel: NoteViewModel,
//    buttonColor: androidx.compose.ui.graphics.Color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
//    buttonBorderColor: androidx.compose.ui.graphics.Color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
//    clearButtonColor: androidx.compose.ui.graphics.Color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
//) {
//    val context = LocalContext.current
//
//    Box(modifier = modifier.fillMaxSize()) {
//        Banner()
//
//        Column(modifier = Modifier.padding(16.dp).padding(top = 48.dp)) {
//            NoteInputFields(
//                title = viewModel.title,
//                onTitleChange = { viewModel.title = it },
//                content = viewModel.content,
//                onContentChange = { viewModel.content = it }
//            )
//            NoteActionButtons(
//                title = viewModel.title,
//                content = viewModel.content,
//                editId = viewModel.editId,
//                onSaveNote = { viewModel.saveNote(context) },
//                onShowClearDialog = { viewModel.showClearDialog = true },
//                buttonColor = buttonColor,
//                buttonBorderColor = buttonBorderColor,
//                clearButtonColor = clearButtonColor
//            )
//            if (viewModel.showClearDialog) {
//                ClearConfirmationDialog(
//                    onClear = { viewModel.clearFields() },
//                    onDismiss = { viewModel.showClearDialog = false }
//                )
//            }
//            viewModel.noteToDelete?.let { note ->
//                DeleteConfirmationDialog(
//                    note = note,
//                    onDelete = {
//                        viewModel.deleteNote()
//                        Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
//                    },
//                    onDismiss = { viewModel.noteToDelete = null }
//                )
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            SortButton(
//                sortOption = viewModel.sortOption,
//                onSortOptionChange = { viewModel.sortOption = it },
//                expanded = viewModel.expanded,
//                onExpandedChange = { viewModel.expanded = it },
//                buttonColor = buttonColor,
//                buttonBorderColor = buttonBorderColor
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            NoteList(
//                notes = viewModel.sortNotes(),
//                onEditNote = { note ->
//                    viewModel.title = note.title
//                    viewModel.content = note.content
//                    viewModel.editId = note.id
//                },
//                onDeleteNote = { viewModel.noteToDelete = it }
//            )
//        }
//        NoteAddButton(
//            onClick = {
//                viewModel.title = ""
//                viewModel.content = ""
//                viewModel.editId = null
//            },
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun NoteAppPreview() {
//    CTWNoteAppTheme {
//        NoteApp(viewModel = NoteViewModel())
//    }
//}