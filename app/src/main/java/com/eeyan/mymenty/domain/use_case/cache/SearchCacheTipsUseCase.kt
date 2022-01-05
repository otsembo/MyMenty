package com.eeyan.mymenty.domain.use_case.cache

import com.eeyan.mymenty.data.repository.HealthRepository
import com.eeyan.mymenty.domain.model.HealthTip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCacheTipsUseCase @Inject constructor(private val repository: HealthRepository) {

    suspend operator fun invoke(query:String) : Flow<List<HealthTip>>{
        return repository.searchLocalTips(query)
    }

}