package com.dahrenericsson.bubblebaerework.domain.repository

import com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase.TagRemoteDataSource
import com.dahrenericsson.bubblebaerework.data.model.TagDto
import com.dahrenericsson.bubblebaerework.data.repository.TagRepositoryImplementation
import com.dahrenericsson.bubblebaerework.domain.common.ErrorHandler
import com.dahrenericsson.bubblebaerework.domain.common.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class TagRepositoryTest {
    @MockK
    private lateinit var mockRemote: TagRemoteDataSource
    private lateinit var repository: TagRepository
    private lateinit var mockErrorHandler: ErrorHandler

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockErrorHandler = mockk(relaxed = true)
        repository = TagRepositoryImplementation(mockRemote, mockErrorHandler)
    }

    @Test
    fun `getTags shouldn't return error when no tags is found`() = runTest {
        // Arrange
        val expectedTags = emptyList<TagDto>()
        coEvery { mockRemote.getTags() } returns expectedTags
        // Act
        val result = repository.getTags()
        // Assert
        assertTrue(result is Result.Success)
    }

    @Test
    fun `getTags should return error when an exception is thrown`() = runTest {
        // Arrange
        val exception = Exception("Network error")
        coEvery { mockRemote.getTags() } throws exception
//        every { mockErrorHandler.handleError(exception) } returns expectedErrorType
        // Act
        val result = repository.getTags()
        // Assert
        assertTrue(result is Result.Error)
    }
}

