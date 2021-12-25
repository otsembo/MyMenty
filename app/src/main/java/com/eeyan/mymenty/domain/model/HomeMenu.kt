package com.eeyan.mymenty.domain.model

import com.eeyan.mymenty.R

object HomeMenu {

    data class Options(val img:Int, val title:String)

    fun getOptionsList() : ArrayList<Options>{
        val optionsList:ArrayList<Options> = ArrayList()
        optionsList.add(Options(R.drawable.ic_my_time, "GET TIME FOR YOURSELF"))
        optionsList.add(Options(R.drawable.ic_books, "INSPIRED STORIES"))
        optionsList.add(Options(R.drawable.ic_sleep, "TIME FOR REST"))
        optionsList.add(Options(R.drawable.ic_my_time, "INSPIRED VIDEOS"))
        return optionsList
    }

}