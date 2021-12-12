package com.eeyan.mymenty.domain.use_case.login

import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase
    @Inject constructor(private val repository: AuthRepository){

    operator fun invoke(email:String, password:String) : Flow<Resource<Boolean>> = flow {

        try {
            //is loading
            emit(Resource.Loading<Boolean>())
            //create account
            val myUserLoggedIn = repository.loginAccount(email, password)
            //success
            emit(Resource.Success<Boolean>(myUserLoggedIn))
        }catch (e : HttpException){
            //error
            emit(Resource.Error<Boolean>(e.localizedMessage?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<Boolean>(e.localizedMessage?: "Check your internet and try again"))
        }catch (e: FirebaseAuthException){
            emit(Resource.Error<Boolean>(e.localizedMessage?: "That email is already in use."))
        }


    }

}