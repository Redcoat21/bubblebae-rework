package com.dahrenericsson.bubblebaerework.domain.common

import io.github.jan.supabase.exceptions.NotFoundRestException
import java.io.IOException


class ErrorHandler {
    fun handleError(error: Throwable): ErrorType {
        return when (error) {
            is IOException -> ErrorType.NETWORK_ERROR
            is NotFoundRestException -> ErrorType.NOT_FOUND_ERROR
            else -> ErrorType.UNKNOWN_ERROR
        }
    }
}