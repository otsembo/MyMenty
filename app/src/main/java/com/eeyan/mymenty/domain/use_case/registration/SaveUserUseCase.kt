package com.eeyan.mymenty.domain.use_case.registration

import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SaveUserUseCase
    @Inject constructor(private val repository: AuthRepository){

        operator fun invoke(email:String, username:String):Flow<Resource<Unit>> = flow {
            try {
                //is loading
                emit(Resource.Loading<Unit>())
                //save data
                val userData = repository.saveUserData(email, username)
                //success
                emit(Resource.Success<Unit>(userData))
            }catch (e : Exception){
                emit(Resource.Error<Unit>(e.message?:"An error occurred"))
            }

        }

}