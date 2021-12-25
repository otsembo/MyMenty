package com.eeyan.mymenty.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eeyan.mymenty.common.BaseState
import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.domain.model.User
import com.eeyan.mymenty.domain.repository.AuthRepository
import com.eeyan.mymenty.domain.use_case.profile.FetchProfileDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileVM
    @Inject
    constructor(private val authRepository: AuthRepository,
                private val fetchProfileDetailsUseCase: FetchProfileDetailsUseCase): ViewModel(){



        private var state:BaseState<User> = BaseState()

        private val _profileState:MutableLiveData<BaseState<User>> = MutableLiveData()
        val profileState:LiveData<BaseState<User>> = _profileState


        fun signOut(){
            authRepository.logoutUser()
        }


        @ExperimentalCoroutinesApi
        fun getUserDetails(){

            fetchProfileDetailsUseCase().onEach {

                when(it){

                    is Resource.Loading -> {
                        state = BaseState(isLoading = true)
                        _profileState.value = state
                    }

                    is Resource.Error -> {
                        state = BaseState(message = it.message, isLoading = false)
                        _profileState.value = state
                    }

                    is Resource.Success -> {
                        state = BaseState(data = it.data, isLoading = false)
                        _profileState.value = state
                    }
                }

            }.launchIn(viewModelScope)

        }


}