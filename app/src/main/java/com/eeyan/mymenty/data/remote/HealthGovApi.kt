package com.eeyan.mymenty.data.remote

import com.eeyan.mymenty.data.remote.dto.MentalHealthData
import retrofit2.http.GET

interface HealthGovApi {

    @GET("topicsearch.json")
    suspend fun getTopData() : MentalHealthData

}