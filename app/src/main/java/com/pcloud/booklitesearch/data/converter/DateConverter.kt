package com.pcloud.booklitesearch.data.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate(value:Long): Date = Date(value)

    @TypeConverter
    fun toLong(date: Date) : Long = date.time
}