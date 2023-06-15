package com.example.citradisi2a.view.detail

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.databinding.ActivityDetailBinding
import com.example.citradisi2a.viewmodel.DetailViewModel
import com.example.citradisi2a.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val food_slug:String = intent.getStringExtra("food_slug")?:"null"
        detailViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailViewModel::class.java]
        if (food_slug != "null") {
            detailViewModel.getDetail(food_slug)
        }

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}
