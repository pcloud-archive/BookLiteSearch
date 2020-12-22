package com.pcloud.booklitesearch.ui.search

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.adapters.ViewBindingAdapter.setOnLongClickListener
import androidx.lifecycle.Observer
import com.pcloud.booklitesearch.R
import com.pcloud.booklitesearch.data.entity.SearchHistory
import com.pcloud.booklitesearch.databinding.ActivitySearchBinding
import com.pcloud.booklitesearch.ui.base.BaseActivity
import com.pcloud.booklitesearch.ui.booklist.BookListActivity
import com.pcloud.booklitesearch.util.EventWraper.EventObserver
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchActivity: BaseActivity<ActivitySearchBinding>() {
    val searchHistoryTextViewClickListener = View.OnLongClickListener {
        var item = ClipData.Item(it.tag as CharSequence)
        var data = ClipData(it.tag as CharSequence, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
        val shadow = View.DragShadowBuilder(it)

        it.startDrag(data, shadow, it, 0)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = getViewModel()
        viewDataBinding.lifecycleOwner = this

        (viewDataBinding.vm as SearchViewModel).startActivityEvent.observe(
            this, EventObserver(this@SearchActivity::callActivity))

        (viewDataBinding.vm as SearchViewModel).visibleSearchHistoryTextViewEvent.observe(
            this, EventObserver(this@SearchActivity::visibleSearchHistoryTextView))

        viewDataBinding.rootLayout.setOnTouchListener { v, event -> doTouch(v, event) }
        viewDataBinding.searchText1.setOnLongClickListener(searchHistoryTextViewClickListener)
        viewDataBinding.searchText2.setOnLongClickListener(searchHistoryTextViewClickListener)
        viewDataBinding.searchText3.setOnLongClickListener(searchHistoryTextViewClickListener)
        viewDataBinding.searchText4.setOnLongClickListener(searchHistoryTextViewClickListener)
        viewDataBinding.searchText5.setOnLongClickListener(searchHistoryTextViewClickListener)
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
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

    private fun visibleSearchHistoryTextView(searchHistoryList: List<SearchHistory>) {
        // TODO visible 된 TextView 탐색 및 unVisible로 변경
        viewDataBinding.searchText1.visibility = View.GONE
        viewDataBinding.searchText2.visibility = View.GONE
        viewDataBinding.searchText3.visibility = View.GONE
        viewDataBinding.searchText4.visibility = View.GONE
        viewDataBinding.searchText5.visibility = View.GONE
        searchHistoryList.forEachIndexed { index, item ->
            var textView: TextView? = null
            when(index) {
                0 ->  textView = viewDataBinding.searchText1
                1 ->  textView = viewDataBinding.searchText2
                2 ->  textView = viewDataBinding.searchText3
                3 ->  textView = viewDataBinding.searchText4
                4 ->  textView = viewDataBinding.searchText5
            }
            textView!!.text = item.text
            textView!!.tag = item.text
            textView!!.visibility = View.VISIBLE
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