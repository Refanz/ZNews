package com.refanzzzz.znews.utils

sealed class ApiState<out R> private constructor(){
    data class Success<out T>(val data: T): ApiState<T>()
    data class Error(val error: String): ApiState<Nothing>()
    data object Loading: ApiState<Nothing>()
}