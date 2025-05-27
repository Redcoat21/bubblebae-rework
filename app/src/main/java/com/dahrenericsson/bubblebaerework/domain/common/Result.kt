package com.dahrenericsson.bubblebaerework.domain.common

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(
        val message: String? = null,
        val type: ErrorType = ErrorType.UNKNOWN_ERROR,
        val exception: Throwable? = null
    ) : Result<Nothing>()
}