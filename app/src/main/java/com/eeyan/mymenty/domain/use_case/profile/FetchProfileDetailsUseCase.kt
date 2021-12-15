package com.eeyan.mymenty.domain.use_case.profile

import com.eeyan.mymenty.common.constants.Constants
import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.domain.model.User
import com.eeyan.mymenty.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class FetchProfileDetailsUseCase
    @Inject
    constructor(private val authRepository: AuthRepository){


        operator fun invoke() : Flow<Resource<User>> = flow {

           try {

               emit(Resource.Loading<User>())

               val user = authRepository.getUserData()

               emit(Resource.Success<User>(user))


           } catch (e: HttpException){

               emit(Resource.Error<User>(e.localizedMessage?: "An unexpected error occurred"))

           } catch (e: IOException){

               emit(Resource.Error<User>(e.localizedMessage?: "Check your internet and try again"))

           }

        }



}