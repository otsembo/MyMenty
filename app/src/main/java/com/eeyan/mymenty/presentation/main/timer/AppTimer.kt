package com.eeyan.mymenty.presentation.main.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eeyan.mymenty.databinding.MainTimerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppTimer : Fragment() {

    private lateinit var binding:MainTimerBinding
    private val viewModel:AppTimerVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initTimer()
        initClicks()
    }

    //initialize observers
    private fun initObservers(){
        viewModel.timeData.observe(viewLifecycleOwner, { time ->
            binding.txtTime.text = time.toString()
        })
    }

    //initialize timers
    private fun initTimer(){
        viewModel.setTimer(30000L)
        viewModel.startTimer()
    }

    //initialize clicks
    private fun initClicks(){
        binding.imgStop.setOnClickListener {
            viewModel.stopTimer()
        }

        binding.imgRestart.setOnClickListener {
            viewModel.restartTimer()
        }
    }

}