package com.dahrenericsson.bubblebaerework.domain.repository

import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.User
import com.dahrenericsson.bubblebaerework.domain.model.UserIdentifier

interface UserRepository {
    suspend fun getUser(identifier: UserIdentifier): Result<User>
}