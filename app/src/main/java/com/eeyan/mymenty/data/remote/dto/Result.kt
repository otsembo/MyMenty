package com.eeyan.mymenty.data.remote.dto

data class Result(
    val Error: String,
    val Language: String,
    val Query: Query,
    val Resources: Resources,
    val Total: Int
)