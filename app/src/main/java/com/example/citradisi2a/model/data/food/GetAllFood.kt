package com.example.citradisi2a.model.data.food



import org.w3c.dom.Text
import java.sql.Date
import java.sql.Timestamp

data class DataMakanan(
    val id:Int,
    val food_name:String,
    val food_slug:String,
    val food_image:String,
    val food_material_id:String,
    val food_material_en:String,
    val food_make_id:String,
    val food_make_en:String,
    val created_at: Timestamp,
    val updated_at: Timestamp,
)
data class GetAllFoodResponse(
    val meta: Meta, val data: List<DataMakanan>, val token:String
)
