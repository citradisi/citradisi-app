package com.example.citradisi2a.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.databinding.ActivityAuthBinding
import com.example.citradisi2a.view.home.HomeActivity
import com.example.citradisi2a.viewmodel.AuthViewModel
import com.example.citradisi2a.viewmodel.ViewModelFactory

class AuthActivity : AppCompatActivity() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding : ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        authViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[AuthViewModel::class.java]
        authViewModel.token.observe(this){
            Log.e("auth activity", it.toString())
            if(it != "null") {
                // Navigate to Home
                navigateToHome()
            }
        }
        authViewModel.msg.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        setContentView(binding.root)

    }

    private fun isValidCredentials(email: String, password: String): Boolean {
        // Perform credential validation logic
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showError(message: String) {
        // Show error message or perform other actions as needed
    }
}
