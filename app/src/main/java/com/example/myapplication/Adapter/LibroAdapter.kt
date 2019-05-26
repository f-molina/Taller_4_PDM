package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.database.Entities.LibroEntity
import kotlinx.android.synthetic.main.rv_item.view.*

class LibroAdapter(var libros: List<LibroEntity>, val clickListener: (LibroEntity) -> Unit) : RecyclerView.Adapter<LibroAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = libros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(libros[position], clickListener)

    fun changeDataSet(newMovieList: List<LibroEntity>) {
        libros = newMovieList
        notifyDataSetChanged()
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        fun bind(movie: LibroEntity, clickListener: (LibroEntity) -> Unit) = with(itemView){
            Glide.with(itemView.context)
                .load(movie.caratula)
                .placeholder(R.drawable.ic_launcher_background)
                .into(book_image)
            book_title.text = movie.titulo
            editorial_tv.text = movie.editorial
            this.setOnClickListener { clickListener(movie) }
        }
    }
}