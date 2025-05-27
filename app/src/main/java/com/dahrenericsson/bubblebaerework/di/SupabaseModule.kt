package com.dahrenericsson.bubblebaerework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://qvaxtrxjirlwhafxtszt.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF2YXh0cnhqaXJsd2hhZnh0c3p0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDgxODI2OTQsImV4cCI6MjA2Mzc1ODY5NH0.Ae_2RTGcZgeSKCbhdm6ULLsYdf1c024b8ZoA-oyzHOE"
        ) {
            install(Postgrest)
            install(Auth)
            install(Realtime)
        }
    }
}