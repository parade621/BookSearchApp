package com.parade621.booksearchapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.parade621.booksearchapp.R
import com.parade621.booksearchapp.data.db.BookSearchDatabase
import com.parade621.booksearchapp.data.repository.BookSearchRepositoryImpl
import com.parade621.booksearchapp.databinding.ActivityMainBinding
import com.parade621.booksearchapp.ui.viewmodel.BookSearchViewModel
import com.parade621.booksearchapp.ui.viewmodel.BookSearchViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupJetpackNavigation()

        val database = BookSearchDatabase.getInstance(this)
        val bookSearchRepository = BookSearchRepositoryImpl(database)
        val factory = BookSearchViewModelFactory(bookSearchRepository, this)

        bookSearchViewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]

    }

    private fun setupJetpackNavigation() {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.booksearch_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}