package com.example.citradisi2a.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citradisi2a.model.data.Auth.BodyRegister
import com.example.citradisi2a.model.data.food.FoodScanImageResponse
import com.example.citradisi2a.model.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class HomeViewModel(val repository: Repository) : ViewModel()  {

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _detailFoodScan = MutableLiveData<FoodScanImageResponse>()
    val detailFoodScan: LiveData<FoodScanImageResponse> = _detailFoodScan

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> = _bitmap

    val token = repository.token

    fun scan(image: Bitmap) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = repository.scanImage(
                    bitmapToMultipart(image)
                )
                _detailFoodScan.value = result.getOrThrow()
                _msg.value = result.getOrThrow().meta.message
                _loading.value = false
            } catch (e: Exception) {
                _msg.value = e.message
                _loading.value = false
            }
        }
    }

    fun bitmapToMultipart(bitmap: Bitmap): MultipartBody.Part {
        val tempFile = File.createTempFile("tempImage", ".jpg")
        val bitmapStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bitmapStream)

        FileOutputStream(tempFile).use { out ->
            out.write(bitmapStream.toByteArray())
        }

        val file = File(tempFile.path)
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("scan_food_image", file.name, requestFile)
    }

    fun storeImage(bitmap: Bitmap, quality: Int) {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val compressedBitmap =
            BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size())
        _bitmap.value = compressedBitmap
    }

}