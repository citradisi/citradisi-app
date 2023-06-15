package com.example.citradisi2a.view.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.citradisi2a.R

/**
 * A simple [Fragment] subclass.
 * Use the [ListFavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_favorite, container, false)
    }


}