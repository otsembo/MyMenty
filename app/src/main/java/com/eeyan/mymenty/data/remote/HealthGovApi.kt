package com.eeyan.mymenty.data.remote

import com.eeyan.mymenty.data.remote.dto.MentalHealthData
import retrofit2.http.GET
import retrofit2.http.Query

interface HealthGovApi {

    @GET("topicsearch.json")
    suspend fun getTopData(@Query("keyword")
                               keyword:String = "general") : MentalHealthData

    @GET("topicsearch.json")
    suspend fun getSearchData(@Query("keyword")
                                  keyword:String) : MentalHealthData

}