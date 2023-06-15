package com.example.citradisi2a.model.data.food


import org.w3c.dom.Text
import java.sql.Date
import java.sql.Timestamp

data class DataFoodDetail(
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
    val loves_count:Int,
    val user_love_status:Int
)
data class FoodDetailResponse(
    val meta: Meta, val data: DataFoodDetail, val token:String
)
