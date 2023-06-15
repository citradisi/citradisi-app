package com.example.citradisi2a.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.FragmentDetailBinding
import com.example.citradisi2a.databinding.FragmentLoginBinding
import com.example.citradisi2a.viewmodel.AuthViewModel
import com.example.citradisi2a.viewmodel.DetailViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        detailViewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        binding = FragmentDetailBinding.inflate(inflater,container,false)

        detailViewModel.foodDetail.observe(requireActivity()) {
            if (it.meta.status != "error") {
                Glide.with(binding.imgItem).load("https://storage.googleapis.com/citradisi/" + it.data.food_image).into(binding.imgItem)
                binding.tvFoodName.text = it.data.food_name
                binding.tvIngredients.text = it.data.food_material_en
                binding.tvInstructions.text = it.data.food_make_en
            }
        }

        return binding.root
    }

}