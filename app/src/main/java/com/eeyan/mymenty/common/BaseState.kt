package com.eeyan.mymenty.common

data class BaseState<T>(
    var data:T? = null,
    var isLoading:Boolean = false,
    var message:String? = null
)
