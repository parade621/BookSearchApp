//package com.parade621.booksearchapp.ui.viewmodel
//
//import android.os.Bundle
//import androidx.lifecycle.AbstractSavedStateViewModelFactory
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.savedstate.SavedStateRegistryOwner
//import androidx.work.WorkManager
//import com.parade621.booksearchapp.data.repository.BookSearchRepository
//
//@Suppress("UNCHECKED_CAST")
//class BookSearchViewModelFactory(
//    private val bookSearchRepository: BookSearchRepository,
//    private val workManager: WorkManager,
//    owner: SavedStateRegistryOwner,
//    defaultArgs: Bundle? = null,
//) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
//    override fun <T : ViewModel> create(
//        key: String,
//        modelClass: Class<T>,
//        handle: SavedStateHandle
//    ): T {
//        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
//            return BookSearchViewModel(bookSearchRepository, workManager, handle) as T
//        }
//        throw IllegalArgumentException("ViewModel class Not found")
//    }
//}