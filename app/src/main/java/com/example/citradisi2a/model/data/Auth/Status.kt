package com.example.citradisi2a.model.data.Auth

import java.sql.Date
import java.sql.Timestamp


data class Meta(
    val code:Int,
    val status:String,
    val message:String
)
data class DataStatus(
    val id:Int,
    val name:String,
    val email:String,
    val email_verified_at: Timestamp,
    val role:String,
    val created_at: Timestamp,
    val updated_at: Timestamp,

    )
data class StatusResponse(
    val meta: Meta, val data: DataStatus, val token:String
)
