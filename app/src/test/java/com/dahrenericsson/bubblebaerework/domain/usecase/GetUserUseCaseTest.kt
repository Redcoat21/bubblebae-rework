package com.dahrenericsson.bubblebaerework.domain.usecase

import com.dahrenericsson.bubblebaerework.data.repository.UserRepositoryImplementation
import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.UserIdentifier
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetUserUseCaseTest {
    @MockK
    private lateinit var userRepository: UserRepositoryImplementation
    private lateinit var useCase: GetUserUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetUserUseCase(userRepository)
    }

    @Test
    fun `invoke should return success result when repository return success`() = runTest {
        // Arrange
        val identifier = mockk<UserIdentifier>()
        every { identifier.isValid() } returns true
        coEvery { userRepository.getUser(identifier) } returns Result.Success(mockk())

        // Act
        val result = useCase(identifier)

        // Assert
        assertTrue(result is Result.Success)
    }

    @Test
    fun `invoke should return validation error when given identifier is invalid`() = runTest {
        // Arrange
        val identifier = mockk<UserIdentifier>()
        every { identifier.isValid() } returns false
        coEvery { userRepository.getUser(identifier) } returns Result.Success(mockk())

        // Act
        val result = useCase(identifier)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).type, ErrorType.VALIDATION_ERROR)
    }

    @Test
    fun `invoke should return error result when repository returns error`() = runTest {
        // Arrange
        val identifier = mockk<UserIdentifier>()
        every { identifier.isValid() } returns true
        coEvery { userRepository.getUser(identifier) } returns Result.Error()

        // Act
        val result = useCase(identifier)

        // Assert
        assertTrue(result is Result.Error)
    }
}