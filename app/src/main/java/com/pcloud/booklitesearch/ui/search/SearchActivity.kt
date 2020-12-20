package com.pcloud.booklitesearch.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.lifecycle.Observer
import com.pcloud.booklitesearch.R
import com.pcloud.booklitesearch.data.entity.SearchHistory
import com.pcloud.booklitesearch.databinding.ActivitySearchBinding
import com.pcloud.booklitesearch.ui.base.BaseActivity
import com.pcloud.booklitesearch.ui.booklist.BookListActivity
import com.pcloud.booklitesearch.util.EventWraper.EventObserver
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchActivity: BaseActivity<ActivitySearchBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = getViewModel()
        viewDataBinding.lifecycleOwner = this

        (viewDataBinding.vm as SearchViewModel).startActivityEvent.observe(
            this, EventObserver(this@SearchActivity::callActivity))

        (viewDataBinding.vm as SearchViewModel).addSearchHistoryTextViewEvent.observe(
            this, EventObserver(this@SearchActivity::addSearchHistoryTextView))

        viewDataBinding.rootLayout.setOnTouchListener { v, event -> doTouch(v, event) }

        (viewDataBinding.vm as SearchViewModel).init()

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    fun doTouch(view:View, event:MotionEvent):Boolean {
        if(event.action == MotionEvent.ACTION_DOWN && viewDataBinding.serachBox.hasFocus() && view.id == R.id.root_layout) {
            viewDataBinding.serachBox.clearFocus()
            hideKeyboard()
            println("TEST SEAR")
        }
        return true
    }

    private fun addSearchHistoryTextView(searchHistoryList: List<SearchHistory>) {
        for(searchHistory in searchHistoryList) {
            val view: TextView = TextView(this)
            view.text = searchHistory.text
            viewDataBinding.rootLayout.addView(view);
        }
    }

    private fun hideKeyboard() {
        var imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.applicationWindowToken, 0)
    }

    private fun callActivity(v:Any) {
        startActivity(Intent(this, BookListActivity::class.java))
    }
}