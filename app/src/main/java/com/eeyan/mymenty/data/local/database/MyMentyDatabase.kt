package com.eeyan.mymenty.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eeyan.mymenty.data.local.dao.HealthTipDao
import com.eeyan.mymenty.domain.model.HealthTip

@Database(entities = [HealthTip::class], version = 1)
abstract class MyMentyDatabase : RoomDatabase() {
    abstract fun healthTipDao() : HealthTipDao
}