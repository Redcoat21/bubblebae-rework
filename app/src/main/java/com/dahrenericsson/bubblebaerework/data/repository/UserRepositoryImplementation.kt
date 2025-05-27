package com.dahrenericsson.bubblebaerework.data.repository

import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.User
import com.dahrenericsson.bubblebaerework.domain.model.UserIdentifier
import com.dahrenericsson.bubblebaerework.domain.repository.UserRepository

class UserRepositoryImplementation : UserRepository {
    override suspend fun getUser(identifier: UserIdentifier): Result<User> {
        TODO("Not yet implemented")
    }
}