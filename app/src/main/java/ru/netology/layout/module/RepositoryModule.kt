package ru.netology.layout.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.netology.layout.repository.PostRepository
import ru.netology.layout.repository.PostRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsPostRepository(impl: PostRepositoryImpl): PostRepository
}