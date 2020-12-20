package com.pcloud.booklitesearch.di

import com.pcloud.booklitesearch.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
}