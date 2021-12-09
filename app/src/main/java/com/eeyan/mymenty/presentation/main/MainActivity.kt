package com.eeyan.mymenty.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eeyan.mymenty.R
import com.eeyan.mymenty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}