package com.eeyan.mymenty.domain.use_case.home

import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.data.remote.dto.getHealthTips
import com.eeyan.mymenty.data.repository.HealthRepository
import com.eeyan.mymenty.domain.model.HealthTip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HealthTipsUseCase
    @Inject
    constructor(private val healthRepository: HealthRepository){


        operator fun invoke() : Flow<Resource<List<HealthTip>>> = flow {

            try {

                emit(Resource.Loading<List<HealthTip>>())

                val healthTip = healthRepository.getHealthTips().Result.Resources.getHealthTips()

                emit(Resource.Success<List<HealthTip>>(data = healthTip))

            }catch (e: IOException){

                emit(Resource.Error<List<HealthTip>>(e.localizedMessage?: "An unexpected error occurred"))

            }catch (e: HttpException){

                emit(Resource.Error<List<HealthTip>>(e.localizedMessage?: "Check your internet connectivity."))
            }

        }

}