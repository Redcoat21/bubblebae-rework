package com.dahrenericsson.bubblebaerework.domain.usecase

import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.Tag
import com.dahrenericsson.bubblebaerework.domain.repository.TagRepository
import javax.inject.Inject

class GetTagsUseCase @Inject constructor(private val repository: TagRepository) {
    suspend operator fun invoke(): Result<List<Tag>> {
        return repository.getTags()
    }
}