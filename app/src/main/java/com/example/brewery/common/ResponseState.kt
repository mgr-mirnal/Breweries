package com.example.brewery.common

sealed class ResponseState{
    object Loading : ResponseState()
    class SUCCESS<T>(val response : T) : ResponseState()
    class ERROR (val error: Exception) : ResponseState()
}