package com.example.citradisi2a.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citradisi2a.model.data.Auth.BodyRegister
import com.example.citradisi2a.model.data.Auth.LoginBody
import com.example.citradisi2a.model.repository.Repository
import kotlinx.coroutines.launch

class AuthViewModel(val repository: Repository) : ViewModel() {

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val token = repository.token

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = repository.login(
                    LoginBody(email, password)
                )
                _msg.value = result.getOrThrow().meta.message
                _loading.value = false
            } catch (e: Exception) {
                _msg.value = e.message
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
           try {
               _loading.value = true
               val result = repository.register(
                   BodyRegister(name, email, password)
               )
               _msg.value = result.getOrThrow().meta.message
               _loading.value = false
           } catch (e: Exception) {
               _msg.value = e.message
           }
        }
    }

}