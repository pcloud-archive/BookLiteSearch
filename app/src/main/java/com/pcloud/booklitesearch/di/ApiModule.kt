package com.pcloud.booklitesearch.di

import com.pcloud.booklitesearch.data.remote.api.KakaoSearchAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(KakaoSearchAPI::class.java) }
}