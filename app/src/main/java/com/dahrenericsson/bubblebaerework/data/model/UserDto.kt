package com.dahrenericsson.bubblebaerework.data.model

import com.dahrenericsson.bubblebaerework.domain.model.User
import java.util.Date

/**
 * User Data Transfer Object (DTO) for transferring user data.
 */
data class UserDto(
    val id: String,
    val email: String,
    val username: String,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) : RemoteMappable<User> {
    companion object {
        const val TABLE_NAME = "users"
    }

    override fun toDomain(): User {
        return User(
            id = id,
            email = email,
            username = username
        )
    }
}