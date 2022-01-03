package com.eeyan.mymenty.presentation.main.timer

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class AppTimerVM
    @Inject
    constructor(): ViewModel() {

    private var time by Delegates.notNull<Long>()
    private lateinit var appTimer: CountDownTimer

    private val _timeData = MutableLiveData<String>()
    val timeData:LiveData<String> = _timeData


    //set timer objects
    fun setTimer(totalTime:Long){
        time = totalTime
    }

    //start timer
    fun startTimer(){
        appTimer = object : CountDownTimer(time, INTERVAL){
            override fun onTick(p0: Long) {
                _timeData.value = formatTime(p0)
            }

            override fun onFinish() {
                _timeData.value = "00:00:00"
            }
        }.start()
    }

    fun stopTimer(){
        appTimer.cancel()
    }

    fun restartTimer(){
        stopTimer()
        startTimer()
    }

    //format time
    private fun formatTime(ms:Long) : String{
        return DateUtils.formatElapsedTime(ms / 1000)
    }

    companion object {
        const val INTERVAL = 1000L
    }

}