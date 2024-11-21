// NoteTypeConverters.kt
package com.example.ctwnoteapp.model

import androidx.room.TypeConverter
import androidx.compose.ui.graphics.Color
import java.util.Date

class NoteTypeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromColor(value: Int): Color {
        return Color(value)
    }

    @TypeConverter
    fun colorToInt(color: Color): Int {
        return color.value.toInt()
    }
}