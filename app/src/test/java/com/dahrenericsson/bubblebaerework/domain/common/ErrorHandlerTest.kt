package com.dahrenericsson.bubblebaerework.domain.common

import io.github.jan.supabase.exceptions.NotFoundRestException
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.IOException

class ErrorHandlerTest {
    private val errorHandler = ErrorHandler()

    @Test
    fun `errorHandler should return NETWORK_ERROR for IOException`() {
        val error = IOException("Network error")
        val result = errorHandler.handleError(error)
        assertEquals(result, ErrorType.NETWORK_ERROR)
    }

    @Test
    fun `errorHandler should return NOT_FOUND_ERROR for NotFoundRestException`() {
        val error = NotFoundRestException("", mockk(relaxed = true))
        val result = errorHandler.handleError(error)
        assertEquals(result, ErrorType.NOT_FOUND_ERROR)
    }

    @Test
    fun `errorHandler should return UNKNOWN_ERROR for other exceptions`() {
        val error = Exception("Unknown error")
        val result = errorHandler.handleError(error)
        assertEquals(result, ErrorType.UNKNOWN_ERROR)
    }
}