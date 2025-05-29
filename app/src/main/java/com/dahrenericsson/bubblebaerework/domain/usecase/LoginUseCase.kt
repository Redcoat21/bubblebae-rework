package com.dahrenericsson.bubblebaerework.domain.usecase

import com.dahrenericsson.bubblebaerework.domain.common.ErrorType
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        if(username.isBlank() || password.isBlank()) {
            return Result.Error(type = ErrorType.VALIDATION_ERROR)
        }
        return authRepository.login(username = username, password = password)
    }
}