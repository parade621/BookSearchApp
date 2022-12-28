package com.parade621.booksearchapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.parade621.booksearchapp.data.model.Book
import com.parade621.booksearchapp.databinding.ItemBookPreviewBinding

class BookSearchViewHolder(
    private val binding: ItemBookPreviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        val author: String =
            book.authors.toString().removeSurrounding("[", "]") // list 형식으로 받기 문에 좌우 대괄호 삭제제
        val publisher: String = book.publisher
        val date: String = if (book.datetime.isNotEmpty()) book.datetime.substring(
            0,
            10
        ) else "" // data는 nullable이기 때문에 방어적 코딩을 실시. 또한 날짜만 가저오기 위해 슬라이싱 실시.

        itemView.apply {
            binding.ivArticleImage.load(book.thumbnail)
            binding.tvTitle.text = book.title
            binding.tvAuthor.text = "$author | $publisher"
            binding.tvDatetime.text = date
        }
    }
}