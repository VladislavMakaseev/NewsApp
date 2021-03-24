package com.vi.newsapp.data.db

import androidx.room.TypeConverter
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TypeConverter {

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime): String {
        return date.format(DateTimeFormatter.ISO_DATE_TIME)
    }

    @TypeConverter
    fun toLocalDateTime(date: String): LocalDateTime {
        return try {
            LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
        } catch (e: Exception) {
            Timber.e(e)
            LocalDateTime.now()
        }
    }

}