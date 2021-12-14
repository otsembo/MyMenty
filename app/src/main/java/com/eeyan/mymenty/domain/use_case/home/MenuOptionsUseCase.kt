package com.eeyan.mymenty.domain.use_case.home

import com.eeyan.mymenty.domain.model.HomeMenu

class MenuOptionsUseCase {

    operator fun invoke() : ArrayList<HomeMenu.Options>{
        return HomeMenu.getOptionsList()
    }


}