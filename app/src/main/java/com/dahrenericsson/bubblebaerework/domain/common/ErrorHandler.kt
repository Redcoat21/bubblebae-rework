package com.dahrenericsson.bubblebaerework.domain.common

import io.github.jan.supabase.exceptions.NotFoundRestException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ErrorHandler @Inject constructor() {
    fun handleError(error: Throwable): ErrorType {
        return when (error) {
            is IOException -> ErrorType.NETWORK_ERROR
            is NotFoundRestException -> ErrorType.NOT_FOUND_ERROR
            else -> ErrorType.UNKNOWN_ERROR
        }
    }
}