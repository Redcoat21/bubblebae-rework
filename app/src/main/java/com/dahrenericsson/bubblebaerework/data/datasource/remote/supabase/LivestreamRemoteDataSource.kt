package com.dahrenericsson.bubblebaerework.data.datasource.remote.supabase

import com.dahrenericsson.bubblebaerework.data.model.LivestreamDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.RealtimeChannel
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class LivestreamRemoteDataSource @Inject constructor(private val client: SupabaseClient) {
    suspend fun getLivestreams(): List<LivestreamDto> {
        val req = client.from(LivestreamDto.TABLE_NAME).select()
        val res = req.decodeList<LivestreamDto>()
        return res
    }

    suspend fun getLivestreamsFlow(): Flow<List<LivestreamDto>> {
        // First get initial data
        val initialData = getLivestreams()
        val livestreamsFlow = MutableStateFlow(initialData)

        // Create and subscribe to the channel
        val channel = client.channel(LivestreamDto.TABLE_NAME)
        channel.subscribe(blockUntilSubscribed = true)

        // Listen for inserts
        listenOnChannelEvent<PostgresAction.Insert>(channel, LivestreamDto.TABLE_NAME) { change ->
            val newItem = getLivestream(change.record["id"].toString())
            newItem?.let {
                val currentList = livestreamsFlow.value
                livestreamsFlow.emit(currentList + it)
            }
        }

        return livestreamsFlow
    }

    suspend fun getLivestream(id: String): LivestreamDto? {
        val req = client.from(LivestreamDto.TABLE_NAME).select {
            filter {
                eq(LivestreamDto::id.name, id)
            }
        }
        val res = req.decodeList<LivestreamDto>()
        return res.firstOrNull()
    }

    private suspend inline fun <reified T : PostgresAction> listenOnChannelEvent(
        channel: RealtimeChannel,
        tableName: String,
        crossinline onEvent: suspend (T) -> Unit
    ) {
        channel.postgresChangeFlow<T>(schema = "public") {
            table = tableName
        }.collect { change ->
            onEvent(change)
        }
    }
}
