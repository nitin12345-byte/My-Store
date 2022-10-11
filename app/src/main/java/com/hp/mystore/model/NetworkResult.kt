package com.hp.mystore.model

sealed class NetworkResult<T>(val data: T? = null, val error: String? = null) {
    class Loading<T>() : NetworkResult<T>()
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Failure<T>(error: String?) : NetworkResult<T>(error = error)
}
