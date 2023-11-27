package com.example.major_assignment2.database

import androidx.room.TypeConverter
import java.util.Date

class MoviesConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}