package com.example.citradisi2a.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.citradisi2a.R
import com.example.citradisi2a.databinding.FragmentBookmarkBinding
import com.example.citradisi2a.databinding.FragmentLoginBinding
import com.example.citradisi2a.view.scan.DetailFragment
import com.example.citradisi2a.viewmodel.AuthViewModel
import com.example.citradisi2a.viewmodel.BookmarkViewModel

class BookmarkFragment : Fragment() {
    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var binding : FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bookmarkViewModel = ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]
        binding = FragmentBookmarkBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

}
