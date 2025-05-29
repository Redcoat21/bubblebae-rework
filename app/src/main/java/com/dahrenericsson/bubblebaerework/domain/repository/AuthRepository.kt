package com.dahrenericsson.bubblebaerework.domain.repository

import com.dahrenericsson.bubblebaerework.domain.common.Result

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun register(username: String, password: String, email: String): Result<Unit>
}