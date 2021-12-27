package com.eeyan.mymenty.common.constants

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Constants {

    //api
    const val BASE_URL = "https://health.gov/myhealthfinder/api/v3/"


    //bundle keys
    const val WEB_DETAILS = "accessUrl"

    //coming soon message
    const val SOON = "Coming soon"

    //snack bar
    fun snackIt(view:View, msg:String){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }

}