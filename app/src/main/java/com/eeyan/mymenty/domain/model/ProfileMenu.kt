package com.eeyan.mymenty.domain.model

import com.eeyan.mymenty.R
import com.eeyan.mymenty.presentation.main.profile.Profile

object ProfileMenu {

    data class ProfileOptions(
        val icon:Int,
        val title:String
    )


    fun getProfileOptions() : ArrayList<ProfileOptions>{
        val options = ArrayList<ProfileOptions>()
        options.add(ProfileOptions(R.drawable.ic_trend, "Your trends"))
        options.add(ProfileOptions(R.drawable.ic_chat, "Chat anonymously"))
        options.add(ProfileOptions(R.drawable.ic_book, "Your personal diary"))
        return options
    }

}