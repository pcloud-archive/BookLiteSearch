package com.pcloud.booklitesearch.ui.booklist

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.pcloud.booklitesearch.R
import com.pcloud.booklitesearch.databinding.ActivityBookListBinding

import com.pcloud.booklitesearch.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class BookListActivity: BaseActivity<ActivityBookListBinding>() {

    override fun getLayoutId() = R.layout.activity_book_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = getViewModel()
        viewDataBinding.lifecycleOwner = this
        (viewDataBinding.vm as BookListViewModel).query = intent.getStringExtra("query")

        (viewDataBinding.vm as BookListViewModel).bookListRequest()
    }
}