package com.example.ctwnoteapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctwnoteapp.model.SortOption
import com.example.ctwnoteapp.ui.theme.CTWNoteAppTheme

@Composable
fun SortButton(
    sortOption: SortOption,
    onSortOptionChange: (SortOption) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    buttonColor: Color,
    buttonBorderColor: Color
) {
    Box {
        Button(
            onClick = { onExpandedChange(true) },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            border = BorderStroke(1.dp, buttonBorderColor),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.size(150.dp, 48.dp)
        ) {
            Text("Sort by: ${sortOption.displayName}", maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            SortOption.values().forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onSortOptionChange(option)
                        onExpandedChange(false)
                    },
                    text = { Text(option.displayName) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SortButtonPreview() {
    CTWNoteAppTheme {
        SortButton(
            sortOption = SortOption.NEWEST,
            onSortOptionChange = {},
            expanded = false,
            onExpandedChange = {},
            buttonColor = MaterialTheme.colorScheme.primary,
            buttonBorderColor = MaterialTheme.colorScheme.onPrimary
        )
    }
}