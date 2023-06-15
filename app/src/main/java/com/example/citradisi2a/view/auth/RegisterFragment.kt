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
import com.example.citradisi2a.databinding.FragmentRegisterBinding
import com.example.citradisi2a.viewmodel.AuthViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding : FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        binding.buttonRegister.setOnClickListener {
            authViewModel.register(
                binding.editTextName.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
        binding.textViewSignIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        return binding.root
    }


}