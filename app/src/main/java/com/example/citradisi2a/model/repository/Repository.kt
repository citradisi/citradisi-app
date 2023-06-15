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
import com.example.citradisi2a.model.data.Love.LoveStoreBody
import com.example.citradisi2a.model.data.Love.MostLoveResponse
import com.example.citradisi2a.model.data.bookmark.BookmarkResponse
import com.example.citradisi2a.model.data.bookmark.BookmarkStoreBody
import com.example.citradisi2a.model.data.food.FoodDetailResponse
import com.example.citradisi2a.model.data.food.FoodScanImageResponse
import com.example.citradisi2a.model.data.food.FoodSearchBody
import com.example.citradisi2a.model.data.food.FoodSearchResponse
import com.example.citradisi2a.model.data.food.GetAllFoodResponse
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

    val token = datastore.getAuthKey().asLiveData()
    suspend fun status(): Boolean {
        val result = api.status(token.value!!)
        return result.meta.status == "success"
    }

    suspend fun getAllFood(): GetAllFoodResponse {
        return api.getAllFood()
    }

    suspend fun foodSearch(foodSearchBody: FoodSearchBody): FoodSearchResponse {
        return api.foodSearch(foodSearchBody)
    }

    suspend fun foodDetail(slug: String): FoodDetailResponse {
        return api.foodDetail(token.value!!, slug)
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

    suspend fun bookmarkUser(): BookmarkResponse {
        return api.bookmarkUser(token.value!!)
    }

    suspend fun bookmarkStore(bookmarkStoreBody: BookmarkStoreBody) {
        val result = api.bookmarkStore(token.value!!, bookmarkStoreBody)
        if (result.meta.status == "success") {
            // jika sukses
        } else {
            // jika error
        }
    }

    suspend fun bookmarkDelete(id: Int): Boolean {
        val result = api.bookmarkDelete(token.value!!, id)
        return result.meta.status == "success"
    }

    suspend fun mostLove(): MostLoveResponse {
        return api.mostLoves(token.value!!)
    }

    suspend fun loveStore(loveStoreBody: LoveStoreBody) {
        val result = api.loveStore(token.value!!, loveStoreBody)
        if (result.meta.status == "success") {
            // Jika sukses
        } else {
            // Jika error
        }
    }

    suspend fun loveDelete(id: Int): Boolean {
        val result = api.loveDelete(token.value!!, id)
        return result.meta.status == "success"
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
