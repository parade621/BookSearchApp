package com.parade621.booksearchapp.ui.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.parade621.booksearchapp.data.model.Book
import com.parade621.booksearchapp.databinding.FragmentSearchBinding
import com.parade621.booksearchapp.ui.adapter.BookSearchAdapter
import com.parade621.booksearchapp.ui.viewmodel.BookSearchViewModel
import com.parade621.booksearchapp.utils.Constants.SEARCH_BOOKS_TIME_DELAY

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    // MainActivity에서 초기화한 ViewModel을 가져온다.
    private lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var bookSearchAdapter: BookSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    // ViewModel을 가져옴.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        setupRecyclerView()
        searchBooks()

        bookSearchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val books: List<Book> = response.documents
            bookSearchAdapter.submitList(books)

        }
    }

    // RecyclerView 설정
    private fun setupRecyclerView() {
        bookSearchAdapter = BookSearchAdapter()
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = bookSearchAdapter
        }
        // click listener 설정
        bookSearchAdapter.setOnItemClickListener {
            val action: NavDirections =
                SearchFragmentDirections.actionFragmentSearchToFragmentBook(it)
            findNavController().navigate(action)
        }
    }


    // 책 검색 주요 함수
    private fun searchBooks() {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.etSearch.text =
            Editable.Factory.getInstance().newEditable(bookSearchViewModel.query)

        // EditText에 텍스트가 입력되면 그 값을 ViewModel에 전달한 후, ViewModel의 SearchBooks가 실행되도록 한다.
        binding.etSearch.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= SEARCH_BOOKS_TIME_DELAY) {
                text?.let {
                    val query: String = it.toString().trim()
                    if (query.isNotEmpty()) {
                        bookSearchViewModel.searchBooks(query)
                        bookSearchViewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}