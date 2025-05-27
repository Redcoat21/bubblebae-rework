package com.dahrenericsson.bubblebaerework.domain.model

interface DomainMappable<T> {
    /**
     * Maps the current object to a remote model of type [T].
     *
     * @return An instance of the domain model.
     */
    fun toRemote(): T
}