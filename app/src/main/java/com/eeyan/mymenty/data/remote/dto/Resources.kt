package com.eeyan.mymenty.data.remote.dto

import com.eeyan.mymenty.domain.model.HealthTip

data class Resources(
    val Resource: List<Resource>
)

fun Resources.getHealthTips() : ArrayList<HealthTip>{
    val tips: ArrayList<HealthTip> = ArrayList()
    for (resource in Resource)
        tips.add(resource.getHealthTip())
    return tips
}