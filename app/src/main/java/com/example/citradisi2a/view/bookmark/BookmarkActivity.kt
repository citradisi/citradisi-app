package com.example.citradisi2a.view.bookmark
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.ActivityAuthBinding
import com.example.citradisi2a.databinding.ActivityBookmarkBinding
import com.example.citradisi2a.model.adapter.BookmarkAdapter
import com.example.citradisi2a.model.adapter.BookmarkItem
import com.example.citradisi2a.viewmodel.AuthViewModel
import com.example.citradisi2a.viewmodel.BookmarkViewModel
import com.example.citradisi2a.viewmodel.ViewModelFactory

class BookmarkActivity : AppCompatActivity() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var binding : ActivityBookmarkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        bookmarkViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[BookmarkViewModel::class.java]
        setContentView(binding.root)

    }

    private fun createDummyData(): List<BookmarkItem> {
        // Create and return a list of dummy bookmark items
        val dummyData = mutableListOf<BookmarkItem>()
        dummyData.add(BookmarkItem("Item 1", "Description 1"))
        dummyData.add(BookmarkItem("Item 2", "Description 2"))
        dummyData.add(BookmarkItem("Item 3", "Description 3"))
        return dummyData
    }
}
