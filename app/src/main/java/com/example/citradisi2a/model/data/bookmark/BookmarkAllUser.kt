package com.example.citradisi2a.model.data.bookmark


import org.w3c.dom.Text
import java.sql.Date
import java.sql.Timestamp


data class Food(
    val id:Int,
    val food_name:String,
    val food_slug:Int,
    val food_image:String,
    val food_material_id: Text,
    val food_material_en: Text,
    val food_make_id: Text,
    val food_make_en: Text,
    val created_at: Timestamp,
    val updated_at: Timestamp
)
data class DataAllBookmark (
    val id:Int,
    val user_id:Int,
    val food_id: Int,
    val bookmark_status:Int,
    val food:Food,
    val created_at: Timestamp,
    val updated_at: Timestamp
    )
data class BookmarkResponse(
    val meta: Meta, val data: List<DataAllBookmark>, val token:String
)
