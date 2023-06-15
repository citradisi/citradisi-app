package com.example.citradisi2a.view.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.FragmentBookmarkBinding
import com.example.citradisi2a.databinding.FragmentListFavoriteBinding
import com.example.citradisi2a.databinding.FragmentLoginBinding
import com.example.citradisi2a.viewmodel.AuthViewModel
import com.example.citradisi2a.viewmodel.BookmarkViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ListFavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFavoriteFragment : Fragment() {
    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var binding : FragmentListFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        bookmarkViewModel = ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]
//        binding = FragmentListFavoriteBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_list_favorite, container, false)
    }


}