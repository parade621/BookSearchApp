package com.parade621.booksearchapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.parade621.booksearchapp.data.model.Book
import com.parade621.booksearchapp.databinding.FragmentBookBinding
import com.parade621.booksearchapp.ui.viewmodel.BookSearchViewModel

class BookFragment : Fragment() {
    private var _binding: FragmentBookBinding? = null
    private val binding: FragmentBookBinding get() = _binding!!

    private val args: BookFragmentArgs by navArgs<BookFragmentArgs>()
    private lateinit var bookSearchViewModel: BookSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        val book: Book = args.book
        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }

        binding.fabFavorite.setOnClickListener {
            bookSearchViewModel.saveBook(book)
            Snackbar.make(view, "Book has saved", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        binding.webview.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}