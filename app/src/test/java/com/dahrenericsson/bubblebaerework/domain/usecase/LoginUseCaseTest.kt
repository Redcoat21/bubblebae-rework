package com.dahrenericsson.bubblebaerework.domain.usecase

import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.repository.AuthRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {
    @MockK
    private lateinit var mockRepository: AuthRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        loginUseCase = LoginUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success when repository returns success`() = runTest {
        // Arrange
        val username = "testUser"
        val password = "testPassword"
        coEvery { mockRepository.login(username, password) } returns Result.Success(Unit)
        // Mock the repository behavior here if needed

        // Act
        val result = loginUseCase(username, password)

        // Assert
        assertTrue(result is Result.Success)
    }

    @Test
    fun `invoke should return validation error when username is empty`() = runTest {
        // Arrange
        val username = ""
        val password = "testPassword"
        coEvery { mockRepository.login(username, password) } returns Result.Success(Unit)

        // Act
        val result = loginUseCase(username, password)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).type, ErrorType.VALIDATION_ERROR)
    }

    @Test
    fun `invoke should return validation error when password is empty`() = runTest {
        // Arrange
        val username = "username"
        val password = ""
        coEvery { mockRepository.login(username, password) } returns Result.Success(Unit)

        // Act
        val result = loginUseCase(username, password)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).type, ErrorType.VALIDATION_ERROR)
    }
}