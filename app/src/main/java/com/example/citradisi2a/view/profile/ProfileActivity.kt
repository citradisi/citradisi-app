package com.example.citradisi2a.view.profile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.example.citradisi2a.databinding.ActivityProfileBinding
import com.example.citradisi2a.view.auth.AuthActivity
import com.example.citradisi2a.viewmodel.ProfileViewModel
import com.example.citradisi2a.viewmodel.ViewModelFactory

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(ProfileViewModel::class.java)

        val logoutButton: ImageView = binding.logoutIcon
//        logoutButton.setOnClickListener {
//            profileViewModel.logout()
//            navigateToLogin()
//        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
