package com.pcloud.booklitesearch.ui.booklist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pcloud.booklitesearch.R
import com.pcloud.booklitesearch.data.remote.domain.Document
import com.pcloud.booklitesearch.util.ReSizeTransformation
import com.squareup.picasso.Picasso

@BindingAdapter(value=["documents", "viewModel"])
fun setDocuments(view: RecyclerView, items:List<Document>, vm:BookListViewModel) {
    view.adapter?.run {
            if (this is DocumentAdapter) {
                this.items = items
                this.notifyDataSetChanged()
            }
        } ?: run {
        DocumentAdapter(items, vm).apply {
            view.adapter = this
        }
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url:String) {
    if(url.isNotEmpty()) {
        Picasso
            .get()
            .load(url)
            .transform(ReSizeTransformation)
            .into(imageView)
    } else {
        Picasso
            .get()
            .load(R.drawable.gm_noimage)
            .transform(ReSizeTransformation)
            .into(imageView)
    }
}

@BindingAdapter("price")
fun IntToString(view: TextView, i:Int) {
    view.text = i.toString()
}

//@BindingAdapter(value=["documents", "viewModel"])
//fun doRefresh(layout:SwipeRefreshLayout, items: ObservableArrayList<Document>, vm:BookListViewModel) {
//    layout.setOnRefreshListener {
//        items.clear()
//        vm.bookListRequest()
//    }
//}