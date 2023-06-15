package com.example.citradisi2a.model.data.Auth

import java.sql.Date
import java.sql.Timestamp

data class RegisterResponse(
    val meta: Meta, val data: DataRegister, val token:String
)

data class DataRegister(
    val id:Int,
    val name:String,
    val email:String,
    val created_at: Timestamp,
    val updated_at: Timestamp
    )
data class BodyRegister(
    val name:String,
    val email:String,
    val password:String
)