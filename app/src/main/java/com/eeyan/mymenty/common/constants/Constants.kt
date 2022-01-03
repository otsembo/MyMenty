package com.eeyan.mymenty.common.constants

import android.view.View
import com.eeyan.mymenty.R
import com.google.android.material.snackbar.Snackbar

object Constants {

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

    //snack bar
    fun snackIt(view:View, msg:String){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }

}