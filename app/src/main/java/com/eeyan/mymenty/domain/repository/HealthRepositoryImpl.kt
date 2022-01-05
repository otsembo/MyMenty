package com.eeyan.mymenty.domain.repository

import com.eeyan.mymenty.data.local.dao.HealthTipDao
import com.eeyan.mymenty.data.local.database.MyMentyDatabase
import com.eeyan.mymenty.data.remote.HealthGovApi
import com.eeyan.mymenty.data.remote.dto.MentalHealthData
import com.eeyan.mymenty.data.repository.HealthRepository
import com.eeyan.mymenty.domain.model.HealthTip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HealthRepositoryImpl
    @Inject
    constructor(private val api:HealthGovApi,
                private val db:MyMentyDatabase,
                private val tipDao: HealthTipDao)
    : HealthRepository {

    override suspend fun getHealthTips(): MentalHealthData {
        return api.getTopData()
    }

    override suspend fun searchTips(query: String): MentalHealthData {
        return api.getSearchData(query)
    }

    override suspend fun fetchLocalTips(): Flow<List<HealthTip>> {
        return tipDao.getDailyTips()
    }

    override suspend fun searchLocalTips(query: String): Flow<List<HealthTip>> {
        return tipDao.searchHelp(query)
    }

    override suspend fun insertTips(healthTips: ArrayList<HealthTip>) {
        tipDao.addAllHealthTips(*healthTips.toTypedArray())
    }


}