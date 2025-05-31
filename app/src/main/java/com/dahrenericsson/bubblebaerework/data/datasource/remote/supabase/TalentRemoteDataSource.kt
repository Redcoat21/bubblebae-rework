package com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase

import com.dahrenericsson.bubblebaerework.data.model.TalentDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject

class TalentRemoteDataSource @Inject constructor(private val client: SupabaseClient) {
    suspend fun getTalents(): List<TalentDto> {
        val columns =
            Columns.list("id", "name", "bio", "username", "talent_tags:talent_tags(tag(*))")
        val req = client.from(TalentDto.TABLE_NAME).select(columns = columns)
        val res = req.decodeList<TalentDto>()
        return res
    }

    suspend fun getTalentById(id: String): TalentDto? {
        val columns =
            Columns.list("id", "name", "bio", "username", "talent_tags:talent_tags(tag(*))")
        val req = client.from(TalentDto.TABLE_NAME).select(columns = columns) {
            filter {
                eq(TalentDto::id.name, id)
            }
        }
        val res = req.decodeList<TalentDto>()
        return res.firstOrNull()
    }
}
