package com.example.citradisi2a.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citradisi2a.model.data.Auth.LoginBody
import com.example.citradisi2a.model.data.food.FoodDetailResponse
import com.example.citradisi2a.model.repository.Repository
import kotlinx.coroutines.launch

class DetailViewModel(val repository: Repository) : ViewModel()  {

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _foodDetail = MutableLiveData<FoodDetailResponse>()
    val foodDetail: LiveData<FoodDetailResponse> = _foodDetail

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val token = repository.token

    fun getDetail(foodSlug: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = repository.foodDetail(foodSlug)
                _msg.value = result.getOrThrow().meta.message
                _foodDetail.value = result.getOrThrow()
                _loading.value = false
            } catch (e: Exception) {
                _msg.value = e.message
            }
        }

    }
}