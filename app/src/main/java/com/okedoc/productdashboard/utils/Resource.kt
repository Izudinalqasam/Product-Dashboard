package com.okedoc.productdashboard.utils

sealed class Resource<T> {
    object Loading
    data class Success<T>(val status: ResourceStatus, val data: T)
    data class Error(val message: String)
}