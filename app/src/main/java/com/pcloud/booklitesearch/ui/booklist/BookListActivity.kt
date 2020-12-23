package com.pcloud.booklitesearch.ui.booklist

import android.os.Bundle
import android.os.PersistableBundle
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
    }

    override fun onResume() {
        super.onResume()
        (viewDataBinding.vm as BookListViewModel).bookListRequest()
    }


}