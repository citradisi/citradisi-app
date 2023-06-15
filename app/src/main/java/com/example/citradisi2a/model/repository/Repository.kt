package com.example.citradisi2a.model.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import com.example.citradisi2a.model.api.ApiService
import com.example.citradisi2a.model.api.Retrofitfood
import com.example.citradisi2a.model.data.Auth.BodyRegister
import com.example.citradisi2a.model.data.Auth.LoginBody
import com.example.citradisi2a.model.data.Auth.LoginResponse
import com.example.citradisi2a.model.data.Auth.RegisterResponse
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
import com.example.citradisi2a.model.data.food.SpecialOfferResponse
import com.example.citradisi2a.model.references.AuthDataStore
import okhttp3.MultipartBody

class Repository(private val api: ApiService, private val datastore: AuthDataStore) {

    suspend fun login(bodylogin: LoginBody): Result<LoginResponse> {
        try {
            val result = api.login(bodylogin)
            if (result.meta.status != "error") {
                datastore.saveAuthKey(result.token)
            }
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun register(register: BodyRegister): Result<RegisterResponse> {
        try {
            val result = api.register(register)
            if (result.meta.status == "success") {
                datastore.saveAuthKey(result.token)
            }
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun logout() {
        try {
            datastore.deleteAuthKey()
        } catch (e: Exception) {

        }
    }

    val token = datastore.getAuthKey().asLiveData()
    suspend fun status(): Boolean {
        val result = api.status("Bearer " + token.value!!)
        return result.meta.status == "success"
    }

    suspend fun getAllFood(): Result<GetAllFoodResponse> {
        try {
            val result = api.getAllFood()
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun foodSearch(foodSearchBody: FoodSearchBody): Result<FoodSearchResponse> {
        try {
            val result = api.foodSearch(foodSearchBody)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun foodDetail(slug: String): Result<FoodDetailResponse> {
        try {
            val result = api.foodDetail("Bearer " + token.value!!, slug)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun specialOffer(): Result<SpecialOfferResponse> {
        try {
            val result = api.specialOffer()
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun scanImage(file: MultipartBody.Part) : Result<FoodScanImageResponse>{
        try {
            Log.e("token value", token.value!!)
            val result = api.foodScan("Bearer " + token.value!!, file)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }

    suspend fun bookmarkUser(): Result<BookmarkResponse> {
        try {
            val result = api.bookmarkUser("Bearer " + token.value!!)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun bookmarkStore(bookmarkStoreBody: BookmarkStoreBody): Result<BookmarkStoreResponse> {
        try {
            val result = api.bookmarkStore("Bearer " + token.value!!, bookmarkStoreBody)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun bookmarkDelete(id: Int): Result<BookmarkDeleteResponse> {
        try {
            val result = api.bookmarkDelete("Bearer " + token.value!!, id)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun mostLove(): Result<MostLoveResponse> {
        try {
            val result = api.mostLoves("Bearer " + token.value!!)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun loveStore(loveStoreBody: LoveStoreBody): Result<LoveStoreResponse> {
        try {
            val result = api.loveStore("Bearer " + token.value!!, loveStoreBody)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun loveDelete(id: Int): Result<LoveDeleteResponse> {
        try {
            val result = api.loveDelete("Bearer " + token.value!!, id)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        private const val PAGE_SIZE = 10
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

        fun getInstance(context: Context): Repository {
            return instance ?: synchronized(Repository::class.java) {
                if (instance == null) {
                    val api = Retrofitfood.getApiService()
                    val datastore = AuthDataStore.getInstance(context.dataStore)
                    instance = Repository(api, datastore)
                }
                return instance as Repository
            }
        }
    }
}
