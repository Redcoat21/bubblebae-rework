package com.dahrenericsson.bubblebaerework.domain.repository

import com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase.AuthRemoteDataSource
import com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase.UserRemoteDataSource
import com.dahrenericsson.bubblebaerework.data.model.UserDto
import com.dahrenericsson.bubblebaerework.data.repository.AuthRepositoryImplementation
import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

class AuthRepositoryTest {
    @MockK
    private lateinit var authRemoteDataSource: AuthRemoteDataSource

    @MockK
    private lateinit var userRemoteDataSource: UserRemoteDataSource
    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        authRepository = AuthRepositoryImplementation(
            authRemote = authRemoteDataSource,
            userRemote = userRemoteDataSource,
            errorHandler = mockk(relaxed = true)

        )
    }

    @Test
    fun `login should return not found error when username doesn't exist`() = runTest {
        // Arrange
        val username = "testUser"
        val password = "testPassword"
        coEvery { userRemoteDataSource.getUserByUsername(username) } returns null

        // Act
        val result = authRepository.login(username, password)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).type, ErrorType.NOT_FOUND_ERROR)
    }

    @Test
    fun `login should return failed when invalid credentials are given`() = runTest {
        // Arrange
        val username = "testUser"
        val password = "wrongPassword"
        val validEmail = "valid@example.com"
        val mockUser = mockk<UserDto>(relaxed = true) {
            every { email } returns validEmail
        }
        val exception = Exception("Invalid credentials")
        coEvery { userRemoteDataSource.getUserByUsername(username) } returns mockUser
        coEvery {
            authRemoteDataSource.login(
                validEmail,
                password
            )
        } throws exception

        // Act
        val result = authRepository.login(username, password)
        // Arrange
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).message, exception.message)
    }

    @Test
    fun `login should return success when valid credentials are given`() = runTest {
        // Arrange
        val username = "testUser"
        val password = "wrongPassword"
        val validEmail = "valid@example.com"
        val mockUser = mockk<UserDto>(relaxed = true) {
            every { email } returns validEmail
        }
        coEvery { userRemoteDataSource.getUserByUsername(username) } returns mockUser
        coEvery {
            authRemoteDataSource.login(
                validEmail,
                password
            )
        } returns Unit

        // Act
        val result = authRepository.login(username, password)
        // Arrange
        assertTrue(result is Result.Success)
    }

    @Test
    fun `register should return error when registration failed`() = runTest {
        // Arrange
        val username = "newUser"
        val password = "newPassword"
        val email = "newEmail"
        val exception = Exception("Registration failed")
        coEvery {
            authRemoteDataSource.register(
                emailInput = email,
                usernameInput = username,
                passwordInput = password
            )
        } throws exception

        // Act
        val result =
            authRepository.register(username = username, password = password, email = email)
        // Arrange
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).message, exception.message)
    }

    @Test
    fun `register should return success when registration succeed`() = runTest {
        // Arrange
        val username = "newUser"
        val password = "newPassword"
        val email = "newEmail"
        coEvery {
            authRemoteDataSource.register(
                emailInput = email,
                usernameInput = username,
                passwordInput = password
            )
        } returns Unit

        // Act
        val result =
            authRepository.register(username = username, password = password, email = email)
        // Arrange
        assertTrue(result is Result.Success)
    }
}