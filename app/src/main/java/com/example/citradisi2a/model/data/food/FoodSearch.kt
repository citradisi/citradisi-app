package com.example.citradisi2a.model.data.food


import org.w3c.dom.Text
import java.sql.Date
import java.sql.Timestamp


data class Meta(
    val code:Int,
    val status:String,
    val message:String
)
data class DataFoodSearch(
    val id:Int,
    val food_name:String,
    val food_slug:String,
    val food_image:String,
    val food_material_id: Text,
    val food_material_en: Text,
    val food_make_id: Text,
    val food_make_en: Text,
    val created_at: Timestamp,
    val updated_at: Timestamp,
)
data class FoodSearchResponse(
    val meta: Meta, val data: List<DataFoodSearch>, val token:String
)
data class FoodSearchBody(
    val search:String
)