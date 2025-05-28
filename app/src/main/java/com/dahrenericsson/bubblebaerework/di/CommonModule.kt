package com.dahrenericsson.bubblebaerework.di

import com.dahrenericsson.bubblebaerework.domain.common.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    /**
     * Provides an instance of [ErrorHandler] for handling errors throughout the application.
     */
    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler {
        return ErrorHandler()
    }
}