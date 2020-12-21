package com.pcloud.booklitesearch.ui.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pcloud.booklitesearch.data.dao.SearchHistoryDao
import com.pcloud.booklitesearch.data.entity.SearchHistory
import com.pcloud.booklitesearch.ui.base.SingleLiveEvent
import com.pcloud.booklitesearch.util.EventWraper.Event
import java.util.*

class SearchViewModel(private val dao: SearchHistoryDao): ViewModel() {
    private val _startActivityEvent = MutableLiveData<Event<Any>>()
    val startActivityEvent: LiveData<Event<Any>>
        get() = _startActivityEvent

    private val _visibleSearchHistoryTextViewEvent = MutableLiveData<Event<List<SearchHistory>>>()
    val visibleSearchHistoryTextViewEvent:LiveData<Event<List<SearchHistory>>>
        get() = _visibleSearchHistoryTextViewEvent

    var searchText:String = ""
    var hintMsg:String = "TEST"
    // 검색
        // 검색 시 히스토리가 남아야함
        // 다음 엑티비티로 이동z
        // 검색을 백그라운드로 해서 booklist에서 보여줘야할지 or booklist에서 검색해서 보여주도록 해야할지..
        // 어차피 booklist도 새로고침 시 검색은 하긴해야함..


    // 텍스트를 입력하지 않는 상태에서 빈 영역 터치 시 포커스 잃기
    // 텍스트를 입력한 상태에서 빈 영역 터치 시 검색
    // 텍스트를 입력한 상태에서 확인 버튼 누르면 검색

    // 처음 등장 시 텍스트 뷰 애니메이션 콜
    fun doFocusChange(hasFocus:Boolean) {
        println("TEST: Focus: $hasFocus | hasFocus.and:${hasFocus.and(false)}  |  searchText:$searchText")
        if(!hasFocus && searchText.isNotEmpty()) {
            doSearch()
            _startActivityEvent.value = Event("")
        }
    }

    fun visibleSearchHistoryTextView(searchHistoryList: List<SearchHistory>) {
        _visibleSearchHistoryTextViewEvent.value = Event(searchHistoryList)
    }

    fun init() {
        val searchHistoryList = dao.findAll()
        visibleSearchHistoryTextView(searchHistoryList)
    }

    fun doSearch() {
        val nowDate = Date(System.currentTimeMillis())
        val searchHistory = SearchHistory(text = searchText, date=nowDate)

        dao.insert(searchHistory)
    }
}