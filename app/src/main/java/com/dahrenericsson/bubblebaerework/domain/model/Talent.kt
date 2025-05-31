package com.dahrenericsson.bubblebaerework.domain.model

import com.dahrenericsson.bubblebaerework.data.model.TalentDto

data class Talent(
    val id: String,
    val name: String,
    val bio: String,
    val username: String,
    val tags: List<Tag>? = null
) : DomainMappable<TalentDto> {
    override fun toRemote(): TalentDto {
        return TalentDto(
            id = id,
            name = name,
            bio = bio,
            username = username,
        )
    }
}