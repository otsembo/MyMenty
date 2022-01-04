package com.eeyan.mymenty.presentation.main.timer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.*
import com.eeyan.mymenty.R
import com.eeyan.mymenty.common.constants.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.min
import kotlin.properties.Delegates

@HiltViewModel
class AppTimerVM
    @Inject
    constructor(): ViewModel() {

    private var time by Delegates.notNull<Long>()
    private lateinit var appTimer: CountDownTimer

    private val _timeData = MutableLiveData<String>()
    val timeData:LiveData<String> = _timeData

    //actual time
    private val _appTime = MutableLiveData<Long>(0L)
    val appTime:LiveData<Long> = _appTime

    //start timer
    fun startTimer(duration:Long){
        appTimer = object : CountDownTimer(duration, INTERVAL){
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
        startTimer(_appTime.value!!)
    }

    //format time
    private fun formatTime(ms:Long) : String{
        return DateUtils.formatElapsedTime(ms / 1000)
    }

    fun setTimer(hour:Int, minute:Int){
        _appTime.value = getCountdownTime(hour, minute)
    }

    fun buildNotification(mCtx:Context){
        val builder = NotificationCompat.Builder(mCtx, CHANNEL_ID)
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(NOTIFICATION_TEXT)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        createNotificationChannel(mCtx)
        showNotification(mCtx, builder)

    }

    private fun createNotificationChannel(mCtx: Context){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                description = CHANNEL_DESC
            }
            val manager = mCtx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(mCtx: Context, builder:NotificationCompat.Builder){
        with(NotificationManagerCompat.from(mCtx)){
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun getCountdownTime(hour:Int, minute:Int) : Long{
        val now = Constants.getMinAndHr()
        val hr = now.first
        val min = now.second

        when {
            hr < hour -> {

                return (((hour - hr) * Constants.HR_IN_MS) +
                        ((minute - min) * Constants.MIN_IN_MS) ).toLong()

            }
            hr == hour -> {

                return if(minute < min){

                    (((24) * Constants.HR_IN_MS) +
                            ((minute - min) * Constants.MIN_IN_MS) ).toLong()
                }else{

                    (((0) * Constants.HR_IN_MS) +
                            ((minute - min) * Constants.MIN_IN_MS) ).toLong()

                }

            }
            else -> {


                return (((24 + hour - hr) * Constants.HR_IN_MS) +
                        ((minute - min) * Constants.MIN_IN_MS) ).toLong()

            }
        }
    }

    companion object {
        const val INTERVAL = 1000L
        const val CHANNEL_ID = "com.eeyan.mymenty"
        const val CHANNEL_NAME = "MyMenty"
        const val CHANNEL_DESC = "MyMenty channel"
        const val NOTIFICATION_ID = 100
        const val NOTIFICATION_TITLE = "Going Already?"
        const val NOTIFICATION_TEXT = "Leaving so soon. It is always important to take time for yourself"
    }

}