package com.example.citradisi2a.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.databinding.FragmentProfileBinding
import com.example.citradisi2a.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding : FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_profile.xml
        profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        binding = FragmentProfileBinding.inflate(inflater,container,false)

        profileViewModel.authUser.observe(requireActivity()) {
            if (it.meta.status != "error") {
                binding.username.text = it.data.name
            }
        }

        return binding.root
    }
}
