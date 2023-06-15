package com.example.citradisi2a.model.data.Love

import java.sql.Date
import java.sql.Timestamp


data class DataLoveStore (
    val id:Int,
    val user_id:Int,
    val food_id: Int,
    val Love_status:Boolean,
    val created_at: Timestamp,
    val updated_at: Timestamp
)
data class LoveStoreResponse(
    val meta: Meta, val data: DataLoveStore, val token:String
)
data class LoveStoreBody(
    val food_id:Int
)
