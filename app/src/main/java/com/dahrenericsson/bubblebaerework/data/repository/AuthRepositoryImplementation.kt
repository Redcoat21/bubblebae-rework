package com.dahrenericsson.bubblebaerework.data.repository

import com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase.AuthRemoteDataSource
import com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase.UserRemoteDataSource
import com.dahrenericsson.bubblebaerework.domain.common.ErrorHandler
import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImplementation @Inject constructor(
    private val authRemote: AuthRemoteDataSource,
    private val userRemote: UserRemoteDataSource,
    private val errorHandler: ErrorHandler
) : AuthRepository {
    override suspend fun login(username: String, password: String): Result<Unit> {
        try {
            val user = userRemote.getUserByUsername(username)
                ?: return Result.Error(message = "User not found", type = ErrorType.NOT_FOUND_ERROR)

            authRemote.login(emailInput = user.email, passwordInput = password)
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(
                message = e.message ?: "An error occurred",
                type = errorHandler.handleError(e)
            )
        }
    }

    override suspend fun register(username: String, password: String, email: String): Result<Unit> {
        try {
            authRemote.register(
                emailInput = email,
                passwordInput = password,
                usernameInput = username
            )
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(
                message = e.message ?: "An error occurred",
                type = errorHandler.handleError(e)
            )
        }
    }
}