package com.pcloud.booklitesearch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pcloud.booklitesearch.data.dao.SearchHistoryDao
import com.pcloud.booklitesearch.data.database.SearchHistoryDatabase.Companion.DB_VERSION
import com.pcloud.booklitesearch.data.entity.SearchHistory

@Database(entities = [SearchHistory::class], version= DB_VERSION, exportSchema=false )
abstract class SearchHistoryDatabase : RoomDatabase() {
    abstract fun getSearchHistoryDao(): SearchHistoryDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "book_lite_search_v3.db"
        @Volatile
        private var INSTANCE: SearchHistoryDatabase? = null

        fun getInstance(context: Context): SearchHistoryDatabase =
            INSTANCE?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, SearchHistoryDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_TO_2)
                .build()

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}