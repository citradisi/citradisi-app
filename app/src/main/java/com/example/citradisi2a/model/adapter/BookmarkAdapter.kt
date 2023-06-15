package com.example.citradisi2a.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.citradisi2a.R


class BookmarkAdapter(private val bookmarkItems: List<BookmarkItem>) :
    RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_main_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookmarkItem = bookmarkItems[position]
        holder.bind(bookmarkItem)
    }

    override fun getItemCount(): Int {
        return bookmarkItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.food_name)
//        private val descriptionTextView: TextView = itemView.findViewById(R.id.food_description)

        fun bind(bookmarkItem: BookmarkItem) {
            titleTextView.text = bookmarkItem.title
//            descriptionTextView.text = bookmarkItem.description
        }
    }
}
