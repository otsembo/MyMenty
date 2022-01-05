package com.eeyan.mymenty.data.repository

import com.eeyan.mymenty.data.remote.dto.MentalHealthData
import com.eeyan.mymenty.domain.model.HealthTip
import kotlinx.coroutines.flow.Flow

interface HealthRepository {

    suspend fun getHealthTips(): MentalHealthData

    suspend fun searchTips(query:String) : MentalHealthData

    suspend fun fetchLocalTips() : Flow<List<HealthTip>>

    suspend fun searchLocalTips(query: String) : Flow<List<HealthTip>>

    suspend fun insertTips(healthTips: ArrayList<HealthTip>)

}