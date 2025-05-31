package com.dahrenericsson.bubblebaerework.domain.repository

import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.Tag

interface TagRepository {
    suspend fun getTags(): Result<List<Tag>>
}