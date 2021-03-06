package com.eeyan.mymenty.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eeyan.mymenty.common.BaseState
import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.data.repository.HealthRepository
import com.eeyan.mymenty.domain.model.HealthTip
import com.eeyan.mymenty.domain.use_case.search.SearchTipUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchVM
    @Inject
    constructor(private val searchTipUseCase: SearchTipUseCase)

    : ViewModel() {

    //health tips state
    private var state: BaseState<List<HealthTip>> = BaseState()

    private val _tipsState: MutableLiveData<BaseState<List<HealthTip>>> = MutableLiveData()
    val tipsState: LiveData<BaseState<List<HealthTip>>> = _tipsState


    fun searchHelp(query:String){

        searchTipUseCase(query).onEach {
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