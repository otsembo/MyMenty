package com.eeyan.mymenty.domain.use_case.cache

import com.eeyan.mymenty.data.repository.HealthRepository
import com.eeyan.mymenty.domain.model.HealthTip
import javax.inject.Inject

class CacheTipsUseCase
    @Inject constructor(private val repository: HealthRepository){

    suspend operator fun invoke(tips:ArrayList<HealthTip>) {
        repository.insertTips(tips)
    }

}