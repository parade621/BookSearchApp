package com.parade621.booksearchapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.parade621.booksearchapp.data.model.Book
import com.parade621.booksearchapp.databinding.ItemBookPreviewBinding

class BookSearchAdapter :
    androidx.recyclerview.widget.ListAdapter<Book, BookSearchViewHolder>(BookDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        return BookSearchViewHolder(
            ItemBookPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // 데이터를 바인딩 시킨다.
    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        val book: Book = currentList[position]
        holder.bind(book)
    }

    // DiffUtil 작동을 위한 callback 생성
    companion object {
        private val BookDiffCallback: DiffUtil.ItemCallback<Book> =
            object : DiffUtil.ItemCallback<Book>() {
                override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                    return oldItem.isbn == newItem.isbn
                }

                override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                    return oldItem == newItem
                }
            }
    }
}