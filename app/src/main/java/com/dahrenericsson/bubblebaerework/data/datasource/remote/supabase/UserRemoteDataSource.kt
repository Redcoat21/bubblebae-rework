package com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase

import com.dahrenericsson.bubblebaerework.data.model.UserDto
import io.github.jan.supabase.SupabaseClient
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val client: SupabaseClient) {
    suspend fun getUserById(id: String): UserDto? {
//        val req = client.from(UserDto.TABLE_NAME).select() {
//            filter {
//                eq(UserDto::id.name, id)
//            }
//        }
//        val res = req.decodeSingleOrNull<UserDto>()
//        return res
        return null
    }
}