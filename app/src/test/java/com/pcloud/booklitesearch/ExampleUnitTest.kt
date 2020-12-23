package com.pcloud.booklitesearch

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.pcloud.booklitesearch.data.remote.domain.Document
import com.pcloud.booklitesearch.data.remote.domain.Meta
import com.pcloud.booklitesearch.data.remote.response.BookListSearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT
import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.DisplayName
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @DisplayName("Kakao Book Search Api")
    @Test
    fun kakaoApi() {
        val service = RetrofitClient.retrofit.create(KakaoApiService::class.java)
        val params = mutableMapOf<String, Any>().apply {
            this["query"] = "Android"
            this["sort"] = "accuracy"
            this["page"] = 1
            this["size"] = 10
        }

        val dispose = service.bookListSearch(params).subscribe({
            println(it.documents)
            println(it.meta)
        }, {})
    }

    object RetrofitClient {
        val retrofit: Retrofit
        val baseUrl = "https://dapi.kakao.com/v3/search/"
        private val CONNECTION_TIMEOUT = 10
        private val READ_TIMEOUT = 30
        private val WRITE_TIMEOUT = 30

        init {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            val connectionPool = ConnectionPool()
            val interceptor = Interceptor {
                it.proceed(it.request().newBuilder().apply {
                    header("Authorization", "KakaoAK 3435476fafc050a6d8fdbbb76ec52577")
                }.build())
            }
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectionPool(connectionPool)
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .build()

            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build()
        }
    }

    interface KakaoApiService {
        @GET("book")
        fun bookListSearch(@QueryMap params: MutableMap<String, Any>): Single<BookListSearchResponse>
    }

    data class BookListSearchResponse (
        @SerializedName("meta") val meta: com.pcloud.booklitesearch.data.remote.domain.Meta,
        @SerializedName("documents") val documents:List<com.pcloud.booklitesearch.data.remote.domain.Document>
    )

    data class Meta (
        @SerializedName("total_count") val totalCount:Int,
        @SerializedName("pageable_count") val pageableCount:Int,
        @SerializedName("is_end") val isEnd:Boolean
    )

    data class Document (
        @SerializedName("authors") val authors:List<String>,
        @SerializedName("contents") val contents:String,
        @SerializedName("isbn") val isbn:String,
        @SerializedName("price") val price:Int,
        @SerializedName("publisher") val publisher:String,
        @SerializedName("sale_price") val salePrice:Int,
        @SerializedName("status") val status:String,
        @SerializedName("thumbnail") val thumbnail:String,
        @SerializedName("title") val title:String,
        @SerializedName("translators") val translators:List<String>,
        @SerializedName("url") val url:String
    )
}