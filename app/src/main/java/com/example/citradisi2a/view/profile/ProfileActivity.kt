package com.example.citradisi2a.view.profile
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.ActivityMainBinding
import com.example.citradisi2a.databinding.ActivityProfileBinding
import com.example.citradisi2a.viewmodel.HomeViewModel
import com.example.citradisi2a.viewmodel.ProfileViewModel
import com.example.citradisi2a.viewmodel.ViewModelFactory

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        profileViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[ProfileViewModel::class.java]
        setContentView(binding.root)

    }
}
