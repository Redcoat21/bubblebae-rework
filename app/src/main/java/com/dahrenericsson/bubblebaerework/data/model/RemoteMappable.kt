package com.dahrenericsson.bubblebaerework.data.model

/**
 * Interface for mapping between a local object and a remote data transfer object (DTO).
 *
 * This interface defines methods for converting an object to a DTO and vice versa.
 *
 * @param T The type of the remote data transfer object.
 */
interface RemoteMappable<T> {
    /**
     * Maps a remote data transfer object (DTO) to the current object.
     *
     * @return An instance of the current object type.
     */
    fun toDomain(): T
}