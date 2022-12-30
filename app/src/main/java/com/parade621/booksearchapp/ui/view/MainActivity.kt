package com.parade621.booksearchapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.parade621.booksearchapp.R
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

//        setupBottomNavigationView()
//        // app이 처음 실행된 경우에만 searchFragment가 실행되게 한다.
//        if (savedInstanceState == null) {
//            binding.bottomNavigationView.selectedItemId = R.id.fragment_search
//        }

        setupJetpackNavigation()

        val bookSearchRepository = BookSearchRepositoryImpl()
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


//    /**
//     * Compose Bottom_Navigation_View
//     */
//    private fun setupBottomNavigationView() {
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.fragment_search -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, SearchFragment())
//                        .commit()
//                    true
//                }
//                R.id.fragment_favorite -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, FavoriteFragment())
//                        .commit()
//                    true
//                }
//                R.id.fragment_settings -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, SettingsFragment())
//                        .commit()
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//}