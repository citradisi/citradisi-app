package com.example.citradisi2a.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.FragmentLoginBinding
import com.example.citradisi2a.viewmodel.AuthViewModel

class LoginFragment : Fragment() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.buttonLogin.setOnClickListener {
            authViewModel.login(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        return binding.root
    }
}