package com.dahrenericsson.bubblebaerework.di

import com.dahrenericsson.bubblebaerework.data.repository.AuthRepositoryImplementation
import com.dahrenericsson.bubblebaerework.data.repository.TagRepositoryImplementation
import com.dahrenericsson.bubblebaerework.data.repository.UserRepositoryImplementation
import com.dahrenericsson.bubblebaerework.domain.repository.AuthRepository
import com.dahrenericsson.bubblebaerework.domain.repository.TagRepository
import com.dahrenericsson.bubblebaerework.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        impl: UserRepositoryImplementation
    ): UserRepository {
        return impl
    }

    @Provides
    fun provideAuthRepository(
        impl: AuthRepositoryImplementation
    ): AuthRepository {
        return impl
    }

    @Provides
    fun provideTagRepository(
        impl: TagRepositoryImplementation
    ): TagRepository {
        return impl
    }
}