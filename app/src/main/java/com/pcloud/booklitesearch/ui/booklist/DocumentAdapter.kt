package com.pcloud.booklitesearch.ui.booklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pcloud.booklitesearch.R
import com.pcloud.booklitesearch.data.remote.domain.Document
import com.pcloud.booklitesearch.databinding.ItemBookBinding
import com.pcloud.booklitesearch.ui.BindingViewHolder

class DocumentAdapter(var items:List<Document> = arrayListOf(), val vm:BookListViewModel):
    RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        return DocumentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        holder.binding.document = items[position]
    }

    override fun getItemCount() = items.size

    class DocumentViewHolder(view: View): BindingViewHolder<ItemBookBinding>(view)
}