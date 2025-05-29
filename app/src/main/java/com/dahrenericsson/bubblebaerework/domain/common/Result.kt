package com.dahrenericsson.bubblebaerework.domain.common

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(
        val message: String? = null,
        val type: ErrorType = ErrorType.UNKNOWN_ERROR,
        val exception: Throwable? = null
    ) : Result<Nothing>()
}

fun <T> Result<T>.processResult(
    onSuccess: (data: T) -> Unit,
    onError: (message: String?, type: ErrorType, exception: Throwable?) -> Unit
) {
    when (this) {
        is Result.Success -> onSuccess(data)
        is Result.Error -> onError(message, type, exception)
    }
}