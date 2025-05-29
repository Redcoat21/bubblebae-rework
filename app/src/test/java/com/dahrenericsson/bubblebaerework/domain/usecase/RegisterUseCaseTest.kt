package com.dahrenericsson.bubblebaerework.domain.usecase

import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.repository.AuthRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RegisterUseCaseTest {
    @MockK
    private lateinit var mockRepository: AuthRepository
    private lateinit var registerUseCase: RegisterUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        registerUseCase = RegisterUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success when repository returns success`() = runTest {
        // Arrange
        val username = "testUser"
        val password = "testPassword"
        val email = "testEmail"
        coEvery { mockRepository.register(username, password, email) } returns Result.Success(Unit)
        // Mock the repository behavior here if needed

        // Act
        val result = registerUseCase(username, password, email)

        // Assert
        assertTrue(result is Result.Success)
    }

    @Test
    fun `invoke should return validation error when username is empty`() = runTest {
        // Arrange
        val username = ""
        val password = "testPassword"
        val email = "testEmail"
        coEvery {
            mockRepository.register(
                username,
                password,
                email
            )
        } returns Result.Success(Unit)

        // Act
        val result = registerUseCase(username, password, email)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).type, ErrorType.VALIDATION_ERROR)
    }

    @Test
    fun `invoke should return validation error when password is empty`() = runTest {
        // Arrange
        val username = "username"
        val password = ""
        val email = "testEmail"
        coEvery {
            mockRepository.register(
                username,
                password,
                email
            )
        } returns Result.Success(Unit)

        // Act
        val result = registerUseCase(username, password, email)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).type, ErrorType.VALIDATION_ERROR)
    }

    @Test
    fun `invoke should return validation error when email is empty`() = runTest {
        // Arrange
        val username = "username"
        val password = "password"
        val email = ""
        coEvery {
            mockRepository.register(
                username,
                password,
                email
            )
        } returns Result.Success(Unit)

        // Act
        val result = registerUseCase(username, password, email)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).type, ErrorType.VALIDATION_ERROR)
    }
}