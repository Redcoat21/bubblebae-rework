package com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase

import com.dahrenericsson.bubblebaerework.data.model.UserDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val client: SupabaseClient) {
    suspend fun getUserById(id: String): UserDto? {
        val req = client.from(UserDto.TABLE_NAME).select() {
            filter {
                eq(UserDto::id.name, id)
            }
        }
        val res = req.decodeSingleOrNull<UserDto>()
        return res
    }

    suspend fun getUserByEmail(email: String): UserDto? {
        val req = client.from(UserDto.TABLE_NAME).select() {
            filter {
                eq(UserDto::email.name, email)
            }
        }
        val res = req.decodeSingleOrNull<UserDto>()
        return res
    }

    suspend fun getUserByUsername(username: String): UserDto? {
        val req = client.from(UserDto.TABLE_NAME).select() {
            filter {
                eq(UserDto::username.name, username)
            }
        }
        val res = req.decodeSingleOrNull<UserDto>()
        return res
    }
}