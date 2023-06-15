package com.example.citradisi2a.model.api

import com.example.citradisi2a.model.data.Auth.BodyRegister
import com.example.citradisi2a.model.data.Auth.LoginBody
import com.example.citradisi2a.model.data.Auth.LoginResponse
import com.example.citradisi2a.model.data.Auth.RegisterResponse
import com.example.citradisi2a.model.data.Auth.StatusResponse
import com.example.citradisi2a.model.data.Love.LoveDeleteResponse
import com.example.citradisi2a.model.data.Love.LoveStoreBody
import com.example.citradisi2a.model.data.Love.LoveStoreResponse
import com.example.citradisi2a.model.data.Love.MostLoveResponse
import com.example.citradisi2a.model.data.bookmark.BookmarkDeleteResponse
import com.example.citradisi2a.model.data.bookmark.BookmarkResponse
import com.example.citradisi2a.model.data.bookmark.BookmarkStoreBody
import com.example.citradisi2a.model.data.bookmark.BookmarkStoreResponse
import com.example.citradisi2a.model.data.food.FoodDetailResponse
import com.example.citradisi2a.model.data.food.FoodScanImageResponse
import com.example.citradisi2a.model.data.food.FoodSearchBody
import com.example.citradisi2a.model.data.food.FoodSearchResponse
import com.example.citradisi2a.model.data.food.GetAllFoodResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


interface ApiService {
    @POST("auth/register")
    suspend fun register(
        @Body bodyRegister: BodyRegister

    ): RegisterResponse
    @POST("auth/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse
    @POST("auth/status")
    suspend fun status(
        @Header ("Authorization") token: String,
    ): StatusResponse
    @GET("food")
    suspend fun getAllFood(

    ): GetAllFoodResponse

    @POST("food")
    suspend fun foodSearch(
        @Body foodSearchBody: FoodSearchBody
    ): FoodSearchResponse

    @GET("food/{food_slug}")
    suspend fun foodDetail(
        @Header("Authorization") token: String,
        @Path("food_slug") food_slug:String,
    ): FoodDetailResponse

    @Multipart
    @POST("food/scan")
    suspend fun foodScan(
        @Header("Authorization") token: String,
        @Part scan_food_image: MultipartBody.Part

    ): FoodScanImageResponse
    @GET("loves")
    suspend fun mostLoves(
        @Header("Authorization") token: String,
    ): MostLoveResponse
    @POST("love")
    suspend fun loveStore(
        @Header("Authorization") token: String,
        @Body loveStoreBody: LoveStoreBody
    ): LoveStoreResponse
    @POST("love/{id}")
    suspend fun loveDelete(
        @Header("Authorization") token: String,
        @Path("id") id:Int
    ): LoveDeleteResponse
    @GET("bookmark")
    suspend fun bookmarkUser(
        @Header("Authorization") token: String,
    ): BookmarkResponse
    @POST("bookmark")
    suspend fun bookmarkStore(
        @Header("Authorization") token: String,
        @Body bookmarkStoreBody: BookmarkStoreBody
    ):BookmarkStoreResponse
    @POST("bookmark/{id}")
    suspend fun bookmarkDelete(
        @Header("Authorization") token: String,
        @Path("id") id:Int
    ): BookmarkDeleteResponse

    data class ImageData(
        val imageName: String,
        val imageData: String
    )

}