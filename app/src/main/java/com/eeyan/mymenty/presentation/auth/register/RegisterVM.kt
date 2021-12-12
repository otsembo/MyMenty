package com.eeyan.mymenty.presentation.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eeyan.mymenty.common.BaseState
import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.domain.use_case.registration.RegisterUseCase
import com.eeyan.mymenty.domain.use_case.registration.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterVM
    @Inject
    constructor(private val registerUseCase: RegisterUseCase,
                private val saveUserUseCase: SaveUserUseCase) : ViewModel(){

        //state
        private var state:BaseState<Boolean> = BaseState()

        private val _registrationState:MutableLiveData<BaseState<Boolean>> = MutableLiveData()
        val registrationState:LiveData<BaseState<Boolean>> = _registrationState

        fun registerUser(email:String, password:String){

            registerUseCase(email, password).onEach {

                when(it){

                    is Resource.Success -> {
                        state = BaseState(data = it.data, isLoading = false)
                        _registrationState.value = state
                    }

                    is Resource.Error -> {
                        state = BaseState(message = it.message, isLoading = false)
                        _registrationState.value = state
                    }

                    is Resource.Loading -> {
                        state = BaseState(isLoading = true)
                        _registrationState.value = state
                    }
                }

            }.launchIn(viewModelScope)

        }


        fun saveUserData(email: String, username:String){

            saveUserUseCase(email, username).onEach {
                when(it){

                    is Resource.Success -> {
                        state = BaseState()
                        _registrationState.value = state
                    }

                    is Resource.Error -> {
                        state = BaseState(message = it.message, isLoading = false)
                        _registrationState.value = state
                    }

                    is Resource.Loading -> {
                        state = BaseState(isLoading = true)
                        _registrationState.value = state
                    }
                }
            }.launchIn(viewModelScope)

        }

}