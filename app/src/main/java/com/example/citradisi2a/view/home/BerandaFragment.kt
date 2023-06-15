package com.example.citradisi2a.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.FragmentBerandaBinding
import com.example.citradisi2a.databinding.FragmentLoginBinding
import com.example.citradisi2a.model.adapter.AdapterRecommendation
import com.example.citradisi2a.view.detail.DetailActivity
import com.example.citradisi2a.viewmodel.AuthViewModel
import com.example.citradisi2a.viewmodel.HomeViewModel

class BerandaFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding : FragmentBerandaBinding
    private lateinit var adapter: AdapterRecommendation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = FragmentBerandaBinding.inflate(inflater,container,false)
        homeViewModel.getAllFood.observe(requireActivity()) {
            if (it.meta.status != "error") {
                adapter = AdapterRecommendation(it.data,
                onClick = {food_slug ->
                    navigateToDetail(food_slug)
                })
                binding.rvSubCategory.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvSubCategory.adapter = adapter
            }
        }
        homeViewModel.specialOffer.observe(requireActivity()) {
            if (it.meta.status != "error") {
//                Special Offer disini ygy
                binding.specialOffer.text = it.data.food_name
            }
        }
        return binding.root
    }
    private fun navigateToDetail(food_slug: String) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("food_slug", food_slug)
        startActivity(intent)
    }
}


