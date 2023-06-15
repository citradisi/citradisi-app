package com.example.citradisi2a.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.model.repository.Repository

class ViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Repository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(repository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)->{
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java)->{
                DetailViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}