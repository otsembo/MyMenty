package com.eeyan.mymenty.presentation.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eeyan.mymenty.databinding.NothingFoundBinding


class NothingFound : Fragment(){
    private lateinit var binding:NothingFoundBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NothingFoundBinding.inflate(inflater, container, false)
        return binding.root
    }
}