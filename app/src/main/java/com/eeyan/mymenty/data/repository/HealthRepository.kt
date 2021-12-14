package com.eeyan.mymenty.data.repository

import com.eeyan.mymenty.data.remote.dto.MentalHealthData

interface HealthRepository {

    suspend fun getHealthTips(): MentalHealthData

    suspend fun searchTips(query:String) : MentalHealthData

}