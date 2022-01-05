package com.eeyan.mymenty.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.eeyan.mymenty.domain.model.HealthTip
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthTipDao {

    @Query("SELECT * FROM health_tips LIMIT 4 OFFSET abs(random() % (select count(*) FROM health_tips))")
    fun getDailyTips() : Flow<List<HealthTip>>

    @Query("SELECT * FROM health_tips WHERE title LIKE '%'||:query||'%'")
    fun searchHelp(query : String) : Flow<List<HealthTip>>

    @Insert(onConflict = REPLACE)
    suspend fun addHealthTip(healthTip: HealthTip)

    @Insert(onConflict = REPLACE)
    suspend fun addAllHealthTips(vararg healthTip: HealthTip)

    @Delete
    suspend fun deleteTips(vararg healthTip: HealthTip)

    @Query("DELETE from health_tips")
    suspend fun deleteAllTips()


}