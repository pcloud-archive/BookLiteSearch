package com.pcloud.booklitesearch.ui.booklist

import androidx.lifecycle.MutableLiveData
import com.pcloud.booklitesearch.data.remote.api.KakaoSearchAPI
import com.pcloud.booklitesearch.data.remote.domain.Document
import com.pcloud.booklitesearch.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//private
class BookListViewModel(val api:KakaoSearchAPI) :BaseViewModel() {
private val _items: MutableLiveData<List<Document>> = MutableLiveData(arrayListOf())
// SearchActivity 에서 query를 받음. 일단 받았다 침
    fun bookListRequest() {
        val params = mutableMapOf<String, Any>().apply {
            this["query"] = "Android"
            this["sort"] = "accuracy"
            this["page"] = 1
            this["size"] = 10
        }
        val dispose = api.bookListSearch(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            println(it.documents)
            _items.value = it.documents
            },{}
        )

        addToDisposable(dispose)
        println("Hello")
        println(_items.value)
    }
}