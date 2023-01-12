package com.parade621.booksearchapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.parade621.booksearchapp.data.api.BookSearchApi
import com.parade621.booksearchapp.data.model.Book
import com.parade621.booksearchapp.data.model.SearchResponse
import com.parade621.booksearchapp.utils.Constants.PAGING_SIZE
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class BookSearchPagingSource(
    private val api: BookSearchApi,
    private val query: String,
    private val sort: String
) : PagingSource<Int, Book>() {

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val pageNumber: Int = params.key ?: STARTING_PAGE_INDEX
            val response: Response<SearchResponse> =
                api.searchBooks(query, sort, pageNumber, params.loadSize)
            val endOfPaginationReached: Boolean = response.body()?.meta?.isEnd!!

            val data: List<Book> = response.body()?.documents!!
            val prevKey: Int? = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey: Int? = if (endOfPaginationReached) {
                null
            } else {
                pageNumber + (params.loadSize / PAGING_SIZE)
            }
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}