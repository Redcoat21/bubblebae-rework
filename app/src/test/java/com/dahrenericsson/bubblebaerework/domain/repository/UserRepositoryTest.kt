package com.dahrenericsson.bubblebaerework.domain.repository

import com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase.UserRemoteDataSource
import com.dahrenericsson.bubblebaerework.data.model.UserDto
import com.dahrenericsson.bubblebaerework.data.repository.UserRepositoryImplementation
import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.User
import com.dahrenericsson.bubblebaerework.domain.model.UserIdentifier
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {
    @MockK
    private lateinit var userRemoteDataSource: UserRemoteDataSource
    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        userRepository = UserRepositoryImplementation(userRemoteDataSource)
    }

    @Test
    fun `getUser should return success result with a valid id`() = runTest {
        // Arrange
        val expectedId = "valid_id"
        val identifier = UserIdentifier.Id(expectedId)
        val userDto = mockk<UserDto>(relaxed = true) {
            every { id } returns expectedId
        }
        val user = mockk<User>(relaxed = true) {
            every { id } returns expectedId
        }
        every { userDto.toDomain() } returns user
        coEvery { userRemoteDataSource.getUserById(identifier.id) } returns userDto
        // Act
        val result = userRepository.getUser(identifier)
        // Assert
        assertTrue(result is Result.Success)
        assertEquals((result as Result.Success).data.id, expectedId)
    }

    @Test
    fun `getUser should return success result with a valid email`() = runTest {
        // Arrange
        val expectedEmail = "johndoe@example.com"
        val identifier = UserIdentifier.Email(expectedEmail)
        val userDto = mockk<UserDto>(relaxed = true) {
            every { email } returns expectedEmail
        }
        val user = mockk<User>(relaxed = true) {
            every { email } returns expectedEmail
        }
        every { userDto.toDomain() } returns user
        coEvery { userRemoteDataSource.getUserByEmail(identifier.email) } returns userDto
        // Act
        val result = userRepository.getUser(identifier)
        // Assert
        assertTrue(result is Result.Success)
        assertEquals((result as Result.Success).data.email, expectedEmail)
    }

    @Test
    fun `getUser should return success result with a valid username`() = runTest {
        // Arrange
        val expectedUsername = "expected_username"
        val identifier = UserIdentifier.Username(expectedUsername)
        val userDto = mockk<UserDto>(relaxed = true) {
            every { username } returns expectedUsername
        }
        val user = mockk<User>(relaxed = true) {
            every { username } returns expectedUsername
        }
        every { userDto.toDomain() } returns user
        coEvery { userRemoteDataSource.getUserByUsername(identifier.username) } returns userDto
        // Act
        val result = userRepository.getUser(identifier)
        // Assert
        assertTrue(result is Result.Success)
        assertEquals((result as Result.Success).data.username, expectedUsername)
    }

    @Test
    fun `getUser should return not found error when user does not exist`() = runTest {
        // Arrange
        val identifier = UserIdentifier.Id("non_existent_id")
        coEvery { userRemoteDataSource.getUserById(identifier.id) } returns null
        // Act
        val result = userRepository.getUser(identifier)
        // Assert
        assertTrue(result is Result.Error)
        assertEquals((result as Result.Error).type, ErrorType.NOT_FOUND_ERROR)
    }

    @Test
    fun `getUser should return error result when an exception occurs`() = runTest {
        // Arrange
        val identifier = UserIdentifier.Id("error_id")
        coEvery { userRemoteDataSource.getUserById(identifier.id) } throws Exception("Network error")
        // Act
        val result = userRepository.getUser(identifier)
        // Assert
        assertTrue(result is Result.Error)
    }
}