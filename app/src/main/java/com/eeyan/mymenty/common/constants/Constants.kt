package com.eeyan.mymenty.common.constants

import android.view.View
import com.eeyan.mymenty.R
import com.google.android.material.snackbar.Snackbar
import java.util.*

object Constants {

    const val DB_NAME: String = "MyMenty"

    //api
    const val BASE_URL = "https://health.gov/myhealthfinder/api/v3/"

    //bundle keys
    const val WEB_DETAILS = "accessUrl"

    //coming soon message
    const val SOON = "Coming soon"

    // array of timer names
    val TIMER_TITLE = arrayOf("PERSONAL TIME","REST TIME")
    val TIMER_ICON = arrayOf(R.drawable.ic_my_time, R.drawable.ic_sleep)

    const val TIMER_DIALOG_TITLE = "SET TIME"

    const val HR_IN_MS = (60 * 60 * 1000)
    const val MIN_IN_MS = (60 * 1000)

    //snack bar
    fun snackIt(view:View, msg:String){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }

    fun getMinAndHr() : Pair<Int, Int> {
        val cal = Calendar.getInstance()
        return Pair(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
    }


}