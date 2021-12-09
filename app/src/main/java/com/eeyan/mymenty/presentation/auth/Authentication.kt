package com.eeyan.mymenty.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eeyan.mymenty.R
import com.eeyan.mymenty.databinding.ActivityAuthenticationBinding

class Authentication : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}