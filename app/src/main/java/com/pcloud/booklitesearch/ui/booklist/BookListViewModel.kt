package com.pcloud.booklitesearch.ui.booklist

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pcloud.booklitesearch.data.remote.api.KakaoSearchAPI
import com.pcloud.booklitesearch.data.remote.domain.Document
import com.pcloud.booklitesearch.extension.with
import com.pcloud.booklitesearch.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//private
class BookListViewModel(val api:KakaoSearchAPI) :BaseViewModel() {
    private val _items: MutableLiveData<ObservableArrayList<Document>> = MutableLiveData(ObservableArrayList())
    val items: MutableLiveData<ObservableArrayList<Document>>
        get() = _items
    private val _refresh: MutableLiveData<Boolean> = MutableLiveData(true)
    val refresh:MutableLiveData<Boolean>
        get()= _refresh

    var query:String? = null

// SearchActivity 에서 query를 받음. 일단 받았다 침
    fun bookListRequest() {
        val params = mutableMapOf<String, Any>().apply {
            this["query"] = query!!
            this["sort"] = "accuracy"
            this["page"] = 1
            this["size"] = 10
        }

        addToDisposable(api.bookListSearch(params).with()
            .doOnSubscribe { _refresh.value = true }
            .doOnSuccess { _refresh.value = false }
            .doOnError { _refresh.value = false }
            .subscribe({
                println(it.documents)
                _items.value?.addAll(it.documents)
            },{}
            ))
    }

    fun doRefresh() {
        _items.value?.clear()
        bookListRequest()
    }

//    fun doScrolled(view: RecyclerView, dx:Int, dy:Int) {
//        val lastPosition = (view.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
//        val itemTotalCount = view.adapter!!.itemCount - 1
//
//        if (lastPosition == itemTotalCount) {
//            println("LAST")
//        }
//    }
}