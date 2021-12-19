package com.okedoc.productdashboard.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    class Loading<out T> : Resource<T>()
    data class Success<T>(val successData: T?) : Resource<T>(data = successData)
    data class Error<out T>(val messageError: String) : Resource<T>(message = messageError)
}

fun<T> Context.observeUiState(state: Resource<T>,progress: ProgressBar, onSuccess: (T) -> Unit) {
    when(state) {
        is Resource.Loading -> {
            progress.visibility = View.VISIBLE
        }
        is Resource.Success -> {
            progress.visibility = View.GONE
            onSuccess(state.successData!!)
        }
        is Resource.Error -> {
            progress.visibility = View.GONE
            Toast.makeText(this, state.messageError, Toast.LENGTH_LONG).show()
        }
    }
}