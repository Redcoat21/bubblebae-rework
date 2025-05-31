package com.dahrenericsson.bubblebaerework.domain.usecase

import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.repository.TagRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetTagsUseCaseTest {
    @MockK
    private lateinit var mockRepository: TagRepository
    private lateinit var getTagsUseCase: GetTagsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        // Initialize the use case with the mocked repository
        getTagsUseCase = GetTagsUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success when repository returns success`() = runTest {
        // Arrange
        coEvery { mockRepository.getTags() } returns Result.Success(mockk())

        // Act
        val result = getTagsUseCase()

        // Assert
        // Check that the result is a success
        assertTrue(result is Result.Success)
    }

    @Test
    fun `invoke should return error when repository returns error`() = runTest {
        // Arrange
        val errorMessage = "An error occurred"
        coEvery { mockRepository.getTags() } returns Result.Error(errorMessage)

        // Act
        val result = getTagsUseCase()

        // Assert
        // Check that the result is an error with the expected message
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).message, errorMessage)
    }
}