package com.parade621.booksearchapp.di

import com.parade621.booksearchapp.data.repository.BookSearchRepository
import com.parade621.booksearchapp.data.repository.BookSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBookSearchRepositoty(
        bookSearchRepository: BookSearchRepositoryImpl,
    ): BookSearchRepository
}