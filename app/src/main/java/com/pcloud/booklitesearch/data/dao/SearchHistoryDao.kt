package com.pcloud.booklitesearch.data.dao

import androidx.room.*
import com.pcloud.booklitesearch.data.entity.SearchHistory

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM SearchHistory ORDER BY date ASC")
    fun findAll():List<SearchHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchHistory: SearchHistory)

    @Delete
    fun delete(searchHistory: SearchHistory)

}