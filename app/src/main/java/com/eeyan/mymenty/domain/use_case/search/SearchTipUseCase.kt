package com.eeyan.mymenty.domain.use_case.search

import com.eeyan.mymenty.common.resource.Resource
import com.eeyan.mymenty.data.remote.dto.getHealthTips
import com.eeyan.mymenty.data.repository.HealthRepository
import com.eeyan.mymenty.domain.model.HealthTip
import com.eeyan.mymenty.domain.use_case.cache.CacheTipsUseCase
import com.eeyan.mymenty.domain.use_case.cache.SearchCacheTipsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchTipUseCase @Inject
    constructor(private val healthRepository: HealthRepository,
                private val searchCacheTipsUseCase: SearchCacheTipsUseCase,
                private val cacheTipsUseCase: CacheTipsUseCase){

    operator fun invoke(query:String) : Flow<Resource<List<HealthTip>>> = flow {

        try {

            emit(Resource.Loading<List<HealthTip>>())

            val healthTip = healthRepository.searchTips(query).Result.Resources?.getHealthTips()

            healthTip?.let {
               cacheTipsUseCase(it)
            }

            emit(Resource.Success<List<HealthTip>>(data = healthTip?: ArrayList<HealthTip>()))

        }catch (e: IOException){

            emit(Resource.Error<List<HealthTip>>(e.localizedMessage?: "An unexpected error occurred"))

        }catch (e: HttpException){

            emit(Resource.Error<List<HealthTip>>(e.localizedMessage?: "Check your internet connectivity."))
        }finally {

            searchCacheTipsUseCase(query).collect {
                emit(Resource.Success<List<HealthTip>>(data = it))
            }

        }

    }

}