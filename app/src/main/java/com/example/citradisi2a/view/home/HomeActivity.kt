package com.example.citradisi2a.view.home


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.ActivityMainBinding
import com.example.citradisi2a.model.references.AuthDataStore
import com.example.citradisi2a.model.repository.Repository
import com.example.citradisi2a.view.auth.AuthActivity
import com.example.citradisi2a.view.detail.DetailActivity
import com.example.citradisi2a.viewmodel.HomeViewModel
import com.example.citradisi2a.viewmodel.ViewModelFactory

class HomeActivity() : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding : ActivityMainBinding
    private val cameraPermission = android.Manifest.permission.CAMERA
    private val storagePermission = android.Manifest.permission.READ_EXTERNAL_STORAGE
    private val mediaPermission = android.Manifest.permission.ACCESS_MEDIA_LOCATION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        homeViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[HomeViewModel::class.java]
        homeViewModel.getAllFood()
        homeViewModel.specialOffer()
        homeViewModel.bitmap.observe(this) {
            if (it != null) {
                homeViewModel.scan(it)
            }
        }
        homeViewModel.detailFoodScan.observe(this) {
            Log.e("food scan", it.data.food_name)
            navigateToDetail(it.data.food_slug)
        }

        homeViewModel.msg.observe(this) {
            Log.e("home activity", it)
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.beranda_nav -> {
                    Navigation.findNavController(
                        this, binding.fragmentContainerView2.id
                    ).navigate(R.id.berandaFragment)
                    true
                }
//                R.id.food_nav -> {
//                    Navigation.findNavController(
//                        this, binding.fragmentContainerView2.id
//                    ).navigate(R.id.berandaFragment)
//                    true
//                }
//                R.id.bookmark_nav ->{
//                    Navigation.findNavController(
//                        this, binding.fragmentContainerView2.id
//                    ).navigate(R.id.listFavoriteFragment)
//                    true
//                }
                R.id.scan_nav -> {
                    dispatchTakePictureIntent()
                    true
                }
                R.id.profile_nav -> {
                    Navigation.findNavController(
                        this, binding.fragmentContainerView2.id
                    ).navigate(R.id.profileFragment)
                    true
                }
                else -> false
            }
        }
        setContentView(binding.root)
    }



    private fun navigateToDetail(food_slug: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("food_slug", food_slug)
        startActivity(intent)
        finish()
    }

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val imageBitmap = data?.extras?.get("data") as Bitmap
            homeViewModel.storeImage(imageBitmap,100)
        }
    }
    val documentLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        val contentResolver = this.contentResolver
        val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        homeViewModel.storeImage(imageBitmap, 150)
    }
    fun dispatchTakePictureIntent() {
        val pickImage = "Pick Image"
        val takePhoto = "Take Photo"

        val options = arrayOf<CharSequence>(pickImage, takePhoto)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Image Source")
        builder.setItems(options) { dialog, item ->
            when (options[item]) {
                pickImage -> {
                    if (ContextCompat.checkSelfPermission(this, storagePermission) != PackageManager.PERMISSION_GRANTED && (ContextCompat.checkSelfPermission(this,mediaPermission)) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(storagePermission,mediaPermission),
                            4
                        )
                    } else {
                        // Permission granted, launch file picker intent
                        val intent = Intent(Intent.ACTION_GET_CONTENT)
                        intent.type = "image/*" // only allow image file types
                        documentLauncher.launch(intent.type)
                    }
                }
                takePhoto -> {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            cameraPermission
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // Permission not granted, request it
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(cameraPermission),
                            6
                        )
                    } else {
                        // Permission granted, launch camera intent
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        resultLauncher.launch(intent)
                    }
                }
            }
        }
        builder.show()
    }

}
