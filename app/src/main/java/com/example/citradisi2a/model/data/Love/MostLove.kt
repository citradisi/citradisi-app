package com.example.citradisi2a.model.data.Love

import org.w3c.dom.Text
import java.sql.Date
import java.sql.Timestamp

data class Meta(
    val code:Int,
    val status:String,
    val message:String
)
data class Food(
    val id:Int,
    val food_name:String,
    val food_slug:String,
    val food_image:String,
    val food_material_id: Text,
    val food_material_en: Text,
    val food_make_id: Text,
    val food_make_en: Text,
    val created_at: Timestamp,
    val updated_at: Timestamp

)
data class DataMostLove(
    val food_Id:Int,
    val loves_total:Int,
    val food:Food
)
data class MostLoveResponse(
    val meta: Meta, val data: List<DataMostLove>, val token:String
)
