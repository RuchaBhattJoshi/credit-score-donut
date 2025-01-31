package com.ruchabhattjoshi.cstask.data.remote.api

sealed class ApiException(message: String) : Exception(message) {
    class NetworkException(message: String) : ApiException(message)
    class ServerException(message: String) : ApiException(message)
    class UnknownException(message: String) : ApiException(message)
}