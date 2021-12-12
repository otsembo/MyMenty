package com.eeyan.mymenty.domain.repository

import com.eeyan.mymenty.data.remote.HealthGovApi
import com.eeyan.mymenty.data.remote.dto.MentalHealthData
import com.eeyan.mymenty.data.repository.HealthRepository
import javax.inject.Inject

class HealthRepositoryImpl
    @Inject
    constructor(private val api:HealthGovApi)
    : HealthRepository {

    override suspend fun getHealthTips(): MentalHealthData {
        return api.getTopData()
    }
}