package com.dahrenericsson.bubblebaerework.data.repository

import com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase.UserRemoteDataSource
import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.User
import com.dahrenericsson.bubblebaerework.domain.model.UserIdentifier
import com.dahrenericsson.bubblebaerework.domain.repository.UserRepository
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(
    private val remote: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUser(identifier: UserIdentifier): Result<User> {
        return runCatching { fetchUserDto(identifier) }
            .mapCatching { userDto ->
                userDto?.toDomain() ?: return Result.Error("User not found", ErrorType.NOT_FOUND_ERROR)
            }
            .fold(
                onSuccess = { Result.Success(it) },
                onFailure = {
                    Timber.e(it)
                    Result.Error(it.message ?: "An error occurred", ErrorType.UNKNOWN_ERROR)
                }
            )
    }

    private suspend fun fetchUserDto(identifier: UserIdentifier) = when (identifier) {
        is UserIdentifier.Id -> remote.getUserById(identifier.id)
        is UserIdentifier.Email -> remote.getUserByEmail(identifier.email)
        is UserIdentifier.Username -> remote.getUserByUsername(identifier.username)
    }
}
