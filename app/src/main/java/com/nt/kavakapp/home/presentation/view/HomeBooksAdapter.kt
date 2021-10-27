package com.nt.kavakapp.home.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nt.kavakapp.R
import com.nt.kavakapp.home.core.domain.HomeModel


class HomeBooksAdapter(
    private var books: List<HomeModel?>
) : RecyclerView.Adapter<HomeBooksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.books_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]

        Glide.with(holder.itemView).load(book?.img).into(holder.img)

        holder.author.text = book?.author
        holder.title.text = book?.title
        holder.genre.text = book?.genre
        holder.description.text = book?.description
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val img: ImageView = itemView.findViewById(R.id.book_img)
        val title: TextView = itemView.findViewById(R.id.book_titulo_text)
        val description: TextView = itemView.findViewById(R.id.book_description_text)
        val author: TextView = itemView.findViewById(R.id.book_author_text)
        val genre: TextView = itemView.findViewById(R.id.book_genero_text)
    }
}