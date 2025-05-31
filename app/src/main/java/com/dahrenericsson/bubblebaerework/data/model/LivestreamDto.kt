package com.dahrenericsson.bubblebaerework.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LivestreamDto(
    val id: String,
    val description: String,
    val streamerId: String
) {
    companion object {
        const val TABLE_NAME = "livestreams"
    }
}
