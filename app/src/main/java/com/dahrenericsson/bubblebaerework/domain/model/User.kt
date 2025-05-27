package com.dahrenericsson.bubblebaerework.domain.model

import com.dahrenericsson.bubblebaerework.data.model.UserDto

data class User(
    val id: String,
    val email: String,
    val username: String,
) : DomainMappable<UserDto> {
    override fun toRemote(): UserDto {
        return UserDto(
            id = id,
            email = email,
            username = username
        )
    }
}
