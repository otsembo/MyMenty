package com.eeyan.mymenty.presentation.main.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.navArgs
import coil.load
import com.eeyan.mymenty.common.constants.Constants
import com.eeyan.mymenty.databinding.MainTimerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppTimer : Fragment() {

    private lateinit var binding:MainTimerBinding
    private val viewModel:AppTimerVM by viewModels()
    private lateinit var appTimerArgs: NavArgsLazy<AppTimerArgs>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainTimerBinding.inflate(inflater, container, false)
        appTimerArgs   = navArgs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDialog()
        initObservers()
        initTimer()
        initViews()
        initClicks()
    }

    //show initial dialog
    private fun showDialog(){
        TimerDialog.displayDialog(requireActivity().supportFragmentManager)
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

    //initialize views
    private fun initViews(){
        with(binding){
            val index = appTimerArgs.value.timerMode
            imgTitle.load(Constants.TIMER_ICON[index])
            txtPageTitle.text = Constants.TIMER_TITLE[index]
        }
    }

}