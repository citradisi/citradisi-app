package com.example.citradisi2a.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citradisi2a.databinding.ItemRvMainCategoryFoodrecomendBinding
import com.example.citradisi2a.model.data.food.DataMakanan

class AdapterRecommendation(private val recommendation: List<DataMakanan>, val onClick: (String) -> Unit) : RecyclerView.Adapter<RecommendationHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationHolder {
        val binding = ItemRvMainCategoryFoodrecomendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecommendationHolder(binding)
    }

    override fun getItemCount(): Int {
        return recommendation.size
    }

    override fun onBindViewHolder(holder: RecommendationHolder, position: Int) {
        with(holder) {
            with(binding) {
                foodName.text = recommendation[position].food_name
                Glide.with(imageViewRecommendation).load("https://storage.googleapis.com/citradisi/" + recommendation[position].food_image).into(imageViewRecommendation)
                root.setOnClickListener {
                    onClick(recommendation[position].food_slug)
                }

            }
        }
    }

}

class RecommendationHolder(val binding: ItemRvMainCategoryFoodrecomendBinding) : RecyclerView.ViewHolder (binding.root)