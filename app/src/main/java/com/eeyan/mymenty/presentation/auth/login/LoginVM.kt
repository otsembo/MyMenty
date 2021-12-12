package com.eeyan.mymenty.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eeyan.mymenty.common.BaseState
import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.domain.use_case.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginVM
    @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel(){

    //state
    private var state: BaseState<Boolean> = BaseState()

    private val _loginState: MutableLiveData<BaseState<Boolean>> = MutableLiveData()
    val loginState: LiveData<BaseState<Boolean>> = _loginState

    fun loginUser(email:String, password:String){

        loginUseCase(email, password).onEach {

            when(it){

                is Resource.Success -> {
                    state = BaseState(data = it.data, isLoading = false)
                    _loginState.value = state
                }

                is Resource.Error -> {
                    state = BaseState(message = it.message, isLoading = false)
                    _loginState.value = state
                }

                is Resource.Loading -> {
                    state = BaseState(isLoading = true)
                    _loginState.value = state
                }
            }

        }.launchIn(viewModelScope)

    }

}