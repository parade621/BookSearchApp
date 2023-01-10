package com.parade621.booksearchapp.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.parade621.booksearchapp.data.model.Book
import com.parade621.booksearchapp.data.model.SearchResponse
import com.parade621.booksearchapp.data.repository.BookSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class BookSearchViewModel(
    private val bookSearchRepository: BookSearchRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Api
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response: Response<SearchResponse> =
            bookSearchRepository.searchBooks(query, getSortMode(), 1, 15)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }

    // Room
    fun saveBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepository.insertBooks(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepository.deleteBooks(book)
    }

    //val favoriteBooks: Flow<List<Book>> = bookSearchRepository.getFavoriteBooks()
    // Favorite Fragment의 Lifecycle과 동기화
    val favoriteBooks: StateFlow<List<Book>> = bookSearchRepository.getFavoriteBooks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    // SaveState
    var query = String()
        set(value) {
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: "" // 값이 없으면 공백값 반환
    }

    // 저장과 로드에 사용할 SAVE_STATE_KEY 정의의
    companion object {
        private const val SAVE_STATE_KEY = "query"
    }

    // DataStore
    fun saveSortMode(value: String) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepository.saveSortMode(value)
    }

    suspend fun getSortMode() = withContext(Dispatchers.IO) {
        bookSearchRepository.getSortMode().first()
    }

    // Paging
    val favoritePagingBooks: StateFlow<PagingData<Book>> =
        bookSearchRepository.getFavoritePagingBooks()
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

    private val _searchPagingResult: MutableStateFlow<PagingData<Book>> =
        MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val searchPagingResult: StateFlow<PagingData<Book>> = _searchPagingResult.asStateFlow()

    fun searchBooksPaing(query: String) {
        viewModelScope.launch {
            bookSearchRepository.searchBooksPaging(query, getSortMode())
                .cachedIn(viewModelScope)
                .collect {
                    _searchPagingResult.value = it
                }
        }
    }

}