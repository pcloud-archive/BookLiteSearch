package com.pcloud.booklitesearch.di

import com.pcloud.booklitesearch.data.database.SearchHistoryDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single { SearchHistoryDatabase.getInstance(androidApplication())}
    single(createdAtStart = false) { get<SearchHistoryDatabase>().getSearchHistoryDao() }
}