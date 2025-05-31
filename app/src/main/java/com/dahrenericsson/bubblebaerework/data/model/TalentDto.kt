package com.dahrenericsson.bubblebaerework.data.model

import com.dahrenericsson.bubblebaerework.domain.model.Tag
import com.dahrenericsson.bubblebaerework.domain.model.Talent
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TalentDto(
    val id: String,
    val name: String,
    val bio: String,
    val username: String,
    @SerialName("talent_tags") @Contextual private val talentTags: TalentTagDto? = null
) : RemoteMappable<Talent> {
    companion object {
        const val TABLE_NAME = "public_talents"
    }

    override fun toDomain(): Talent {
        return Talent(
            id = id,
            name = name,
            bio = bio,
            username = username,
            tags = talentTags?.toDomain()
        )
    }
}

@Serializable
data class TalentTagDto(
    @Contextual val tags: List<TagDto>
) : RemoteMappable<List<Tag>> {
    override fun toDomain(): List<Tag> {
        return tags.map { it.toDomain() }
    }
}
