package com.parade621.booksearchapp.data.repository

import androidx.lifecycle.LiveData
import com.parade621.booksearchapp.data.model.Book
import com.parade621.booksearchapp.data.model.SearchResponse
import retrofit2.Response

interface BookSearchRepository {

    suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Response<SearchResponse>

    // Room Dao를 조작하기 위한 메서드
    suspend fun insertBooks(book: Book)

    suspend fun deleteBooks(book: Book)
    
    fun getFavoriteBooks(): LiveData<List<Book>>
}