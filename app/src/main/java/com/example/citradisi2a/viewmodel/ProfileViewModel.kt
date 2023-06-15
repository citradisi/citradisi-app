package com.example.citradisi2a.viewmodel

import androidx.lifecycle.ViewModel
import com.example.citradisi2a.model.repository.Repository
import com.example.citradisi2a.view.profile.ProfileActivity

class ProfileViewModel(private val repository: Repository) : ViewModel() {

    suspend fun logout(profileActivity: ProfileActivity) {
        // Call the logout method of the repository
        repository.logout()
    }
}