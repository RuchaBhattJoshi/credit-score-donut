package com.ruchabhattjoshi.cstask.domain

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<out T>(val data: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()

    fun getSuccessData() = (this as Success).data
    fun getErrorMessage(): String = (this as Error).message
}

