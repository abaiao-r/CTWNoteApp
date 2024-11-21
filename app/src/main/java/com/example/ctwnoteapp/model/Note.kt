// Note.kt
package com.example.ctwnoteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date
import java.util.UUID
import kotlin.random.Random

@Entity(tableName = "notes")
@TypeConverters(NoteTypeConverters::class)
data class Note(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val createdDate: Date = Date(),
    var updatedDate: Date = Date(),
    val color: Int = generateRandomColor(),
    var isDeleted: Boolean = false
)

fun generateRandomColor(): Int {
    return (0xFF000000 or Random.nextInt(0xFFFFFF).toLong()).toInt()
}

enum class SortOption(val displayName: String) {
    NEWEST("Newest"),
    OLDEST("Oldest"),
    ALPHABETICAL("A - Z")
}
