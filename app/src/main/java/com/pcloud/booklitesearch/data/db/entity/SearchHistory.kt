package com.pcloud.booklitesearch.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.pcloud.booklitesearch.data.db.converter.DateConverter
import java.util.*

@Entity(tableName="SearchHistory")
@TypeConverters(DateConverter::class)
data class SearchHistory(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name="text") val text:String,
    @ColumnInfo(name="date") val date: Date
)