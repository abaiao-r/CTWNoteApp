package com.example.ctwnoteapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputFields(
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            //.padding(16.dp)
    ) {
        TextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text("Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .focusRequester(focusRequester),
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Default,
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester.requestFocus() }
            )
        )
        TextField(
            value = content,
            onValueChange = onContentChange,
            label = { Text("Content") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .focusRequester(focusRequester),
            maxLines = 5,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Default,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus().also { focusRequester.freeFocus() } }
            )
        )
    }
}

//@Composable
//fun NoteInputFields(
//    title: String,
//    onTitleChange: (String) -> Unit,
//    content: String,
//    onContentChange: (String) -> Unit
//) {
//    TextField(
//        value = title,
//        onValueChange = onTitleChange,
//        label = { Text("Title") },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 8.dp),
//        maxLines = 1
//    )
//    TextField(
//        value = content,
//        onValueChange = onContentChange,
//        label = { Text("Content") },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 16.dp),
//        maxLines = 5
//    )
//}


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
