package com.vi.newsapp.data.db

import androidx.room.TypeConverter
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TypeConverter {

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): String {
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME)
    }

    @TypeConverter
    fun toLocalDateTime(dateTime: String): LocalDateTime {
        return try {
            LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME)
        } catch (e: Exception) {
            Timber.e(e)
            LocalDateTime.now()
        }
    }

}