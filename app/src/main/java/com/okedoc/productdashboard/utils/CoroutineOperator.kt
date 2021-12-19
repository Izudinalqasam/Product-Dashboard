package com.okedoc.productdashboard.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun<T> debounce(
    waitMs: Long = 1000L,
    scope: CoroutineScope,
    onEmit: (T) -> Unit
): (T, Int, Int, Int) -> Unit {
    var debounceJob: Job? = null
    return { param: T, _, _, _ ->
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(waitMs)
            onEmit(param)
        }
    }
}