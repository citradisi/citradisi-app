package com.example.citradisi2a.view.profile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.databinding.ActivityBookmarkBinding
import com.example.citradisi2a.databinding.ActivityMainBinding

import com.example.citradisi2a.databinding.ActivityProfileBinding
import com.example.citradisi2a.view.auth.AuthActivity
import com.example.citradisi2a.viewmodel.BookmarkViewModel
import com.example.citradisi2a.viewmodel.HomeViewModel
import com.example.citradisi2a.viewmodel.ProfileViewModel
import com.example.citradisi2a.viewmodel.ViewModelFactory

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel.authUser()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        profileViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[ProfileViewModel::class.java]
        setContentView(binding.root)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
