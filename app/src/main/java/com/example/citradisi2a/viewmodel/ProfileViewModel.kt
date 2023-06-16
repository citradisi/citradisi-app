package com.example.citradisi2a.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citradisi2a.model.data.Auth.StatusResponse
import com.example.citradisi2a.model.repository.Repository
import com.example.citradisi2a.view.profile.ProfileActivity
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository) : ViewModel() {
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _authUser = MutableLiveData<StatusResponse>()
    val authUser: LiveData<StatusResponse> = _authUser

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    fun authUser() {
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = repository.status()
                _authUser.value = result.getOrThrow()
                _msg.value = result.getOrThrow().meta.message
                _loading.value = false
            } catch (e: Exception) {
                _msg.value = e.message
                _loading.value = false
            }
        }
    }
}