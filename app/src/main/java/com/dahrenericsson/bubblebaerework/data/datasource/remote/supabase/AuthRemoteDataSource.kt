package com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val client: SupabaseClient) {
    suspend fun login(emailInput: String, passwordInput: String) {
        client.auth.signInWith(Email) {
            email = emailInput
            password = passwordInput
        }
    }

    suspend fun register(emailInput: String, passwordInput: String, usernameInput: String) {
        client.auth.signUpWith(Email) {
            email = emailInput
            password = passwordInput
            data = buildJsonObject {
                put("username", JsonPrimitive(usernameInput))
            }
        }
        // Force sign in after registration
        client.auth.signOut()
    }
}