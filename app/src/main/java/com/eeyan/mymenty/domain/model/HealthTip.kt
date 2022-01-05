package com.eeyan.mymenty.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_tips")
data class HealthTip(
    @PrimaryKey
    val id:String,
    val title:String,
    val desc:String,
    val image:String,
    val url:String
)
