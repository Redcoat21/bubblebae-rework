package com.dahrenericsson.bubblebaerework.data.repository

import com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase.TagRemoteDataSource
import com.dahrenericsson.bubblebaerework.domain.common.ErrorHandler
import com.dahrenericsson.bubblebaerework.domain.common.Result
import com.dahrenericsson.bubblebaerework.domain.model.Tag
import com.dahrenericsson.bubblebaerework.domain.repository.TagRepository
import javax.inject.Inject

class TagRepositoryImplementation @Inject constructor(
    private val remote: TagRemoteDataSource,
    private val errorHandler: ErrorHandler
) :
    TagRepository {
    override suspend fun getTags(): Result<List<Tag>> {
        return runCatching { remote.getTags() }.mapCatching { talentDto -> talentDto.map { it.toDomain() } }
            .fold(onSuccess = {
                Result.Success(it)
            }, onFailure = {
                Result.Error(it.message ?: "An error occurred", errorHandler.handleError(it))
            })
    }
}