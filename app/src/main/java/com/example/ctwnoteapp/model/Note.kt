// Note.kt
package com.example.ctwnoteapp.model

import androidx.compose.ui.graphics.Color
import java.util.Date
import java.util.UUID
import kotlin.random.Random

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val createdDate: Date = Date(),
    var updatedDate: Date = Date(),
    val color: Color = generateRandomColor(),
    var isDeleted: Boolean = false
)

enum class SortOption(val displayName: String) {
    NEWEST("Newest"),
    OLDEST("Oldest"),
    ALPHABETICAL("A - Z")
}

fun generateRandomColor(): Color {
    return Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
}
