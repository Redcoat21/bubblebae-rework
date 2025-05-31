package com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase

import com.dahrenericsson.bubblebaerework.data.model.TagDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class TagRemoteDataSource @Inject constructor(private val client: SupabaseClient) {
    suspend fun getTags(): List<TagDto> {
        val req = client.from(TagDto.TABLE_NAME).select()
        val res = req.decodeList<TagDto>()
        return res
    }
}