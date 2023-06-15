package com.example.citradisi2a.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.FragmentBerandaBinding
import com.example.citradisi2a.databinding.FragmentLoginBinding
import com.example.citradisi2a.viewmodel.AuthViewModel
import com.example.citradisi2a.viewmodel.HomeViewModel

class BerandaFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding : FragmentBerandaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = FragmentBerandaBinding.inflate(inflater,container,false)
        return binding.root
    }
}
