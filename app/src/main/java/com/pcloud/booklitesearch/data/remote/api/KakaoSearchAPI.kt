package com.pcloud.booklitesearch.data.remote.api

import com.pcloud.booklitesearch.data.remote.response.BookListSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface KakaoSearchAPI {

    @GET("book")
    fun bookListSearch(@QueryMap params: MutableMap<String, Any>): Single<BookListSearchResponse>

}