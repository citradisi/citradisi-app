package com.example.citradisi2a.view.scan

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.hardware.camera2.params.StreamConfigurationMap
import android.media.Image
import android.media.ImageReader
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.util.Size
import android.util.SparseIntArray
import android.view.Surface
import android.view.TextureView

import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.ActivityProfileBinding
import com.example.citradisi2a.databinding.ActivityScanBinding
import com.example.citradisi2a.viewmodel.ProfileViewModel
import com.example.citradisi2a.viewmodel.ScanViewModel
import com.example.citradisi2a.viewmodel.ViewModelFactory
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*

class ScanActivity : AppCompatActivity() {

    private lateinit var scanViewModel: ScanViewModel
    private lateinit var binding : ActivityScanBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        scanViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[ScanViewModel::class.java]
        setContentView(binding.root)


    }


}
