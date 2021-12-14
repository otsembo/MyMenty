package com.eeyan.mymenty.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eeyan.mymenty.common.BaseState
import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.domain.model.HealthTip
import com.eeyan.mymenty.domain.model.HomeMenu
import com.eeyan.mymenty.domain.use_case.home.HealthTipsUseCase
import com.eeyan.mymenty.domain.use_case.home.MenuOptionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeVM
    @Inject
    constructor(private val healthTipsUseCase: HealthTipsUseCase,
                menuOptionsUseCase: MenuOptionsUseCase) : ViewModel(){

        //health tips state
        private var state:BaseState<List<HealthTip>> = BaseState()

        private val _tipsState:MutableLiveData<BaseState<List<HealthTip>>> = MutableLiveData()
        val tipsState:LiveData<BaseState<List<HealthTip>>> = _tipsState

        //options menu
        private lateinit var _optionsList: ArrayList<HomeMenu.Options>
        val optionsList
            get() = _optionsList


        init {

            getTips()

            _optionsList = menuOptionsUseCase()

        }

        private fun getTips(){

            healthTipsUseCase().onEach {

                when(it){

                    is Resource.Success -> {
                        state = BaseState(data = it.data, isLoading = false)
                        _tipsState.value = state
                    }

                    is Resource.Error -> {
                        state = BaseState(message = it.message, isLoading = false)
                        _tipsState.value = state
                    }

                    is Resource.Loading -> {
                        state = BaseState(isLoading = true)
                        _tipsState.value = state
                    }
                }

            }.launchIn(viewModelScope)

        }

}