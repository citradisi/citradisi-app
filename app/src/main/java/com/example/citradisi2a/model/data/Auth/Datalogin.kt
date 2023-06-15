package com.example.citradisi2a.model.data.Auth

import java.sql.Date
import java.sql.Timestamp

data class Datalogin(
    val id:Int,
    val name:String,
    val email:String,
    val email_verified_at:Timestamp,
    val role:String,
    val created_at:Timestamp,
    val updated_at:Timestamp,

)

data class LoginResponse(
    val meta: Meta, val data: Datalogin, val token:String
)
data class LoginBody(
    val email:String,
    val password:String
)
