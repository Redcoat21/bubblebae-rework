package com.dahrenericsson.bubblebaerework.data.model

import com.dahrenericsson.bubblebaerework.domain.model.Tag
import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    val id: Int,
    val name: String
) : RemoteMappable<Tag> {

    override fun toDomain(): Tag {
        return Tag(
            id = id,
            name = name
        )
    }

    companion object {
        const val TABLE_NAME = "tags"
    }
}