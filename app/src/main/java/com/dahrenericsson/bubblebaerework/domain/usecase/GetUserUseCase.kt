package com.dahrenericsson.bubblebaerework.domain.usecase

import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.User
import com.dahrenericsson.bubblebaerework.domain.model.UserIdentifier
import com.dahrenericsson.bubblebaerework.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(identifier: UserIdentifier): Result<User> {
        if(!identifier.isValid()) {
            return Result.Error(type = ErrorType.VALIDATION_ERROR)
        }
        return userRepository.getUser(identifier)
    }
}