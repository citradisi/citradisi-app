package com.example.citradisi2a.model.data.bookmark

import java.sql.Date
import java.sql.Timestamp

data class Meta(
    val code:Int,
    val status:String,
    val message:String
)
data class Data (
    val id:Int,
    val user_id:Int,
    val food_id: Int,
    val bookmark_status:Boolean,
    val created_at: Timestamp,
    val updated_at: Timestamp
)
data class BookmarkStoreResponse(
    val meta: Meta, val data: Data, val token:String
)
data class BookmarkStoreBody(
    val food_id:Int
)