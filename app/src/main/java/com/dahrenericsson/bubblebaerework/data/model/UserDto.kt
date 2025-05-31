package com.dahrenericsson.bubblebaerework.data.model

import com.dahrenericsson.bubblebaerework.domain.model.User
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

/**
 * User Data Transfer Object (DTO) for transferring user data.
 */
@Serializable
data class UserDto(
    val id: String = "",
    val email: String,
    val username: String,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = Clock.System.now()
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