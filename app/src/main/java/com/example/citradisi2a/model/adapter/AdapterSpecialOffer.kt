package com.example.citradisi2a.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citradisi2a.databinding.ItemRvMainCategoryBinding
import com.example.citradisi2a.databinding.ItemRvMainCategoryFoodrecomendBinding
import com.example.citradisi2a.model.data.food.DataSpecialOffer


class AdapterSpecialOffer(private val recommendation: List<DataSpecialOffer>, val onClick: (String) -> Unit) : RecyclerView.Adapter<SpecialOfferHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialOfferHolder {
        val binding = ItemRvMainCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SpecialOfferHolder(binding)
    }

    override fun getItemCount(): Int {
        return recommendation.size
    }

    override fun onBindViewHolder(holder: SpecialOfferHolder, position: Int) {
        with(holder) {
            with(binding) {
                specialFoodName.text = recommendation[position].food_name
                Glide.with(specialFoodImage).load("https://storage.googleapis.com/citradisi/" + recommendation[position].food_image).into(specialFoodImage)
                root.setOnClickListener {
                    onClick(recommendation[position].food_slug)
                }

            }
        }
    }

}

class SpecialOfferHolder(val binding: ItemRvMainCategoryBinding) : RecyclerView.ViewHolder (binding.root)