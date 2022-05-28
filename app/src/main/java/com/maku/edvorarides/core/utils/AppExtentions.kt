package com.maku.edvorarides.core.utils

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun CoroutineScope.createExceptionHandler(
    message: String,
    crossinline action: (throwable: Throwable) -> Unit,
) = CoroutineExceptionHandler { _, throwable ->
    Log.e("Throwable", "${throwable} ${message}")
    throwable.printStackTrace()

    launch {
        action(throwable)
    }
}