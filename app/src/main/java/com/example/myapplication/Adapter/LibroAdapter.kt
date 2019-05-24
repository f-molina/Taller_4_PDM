package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.database.Entities.LibroEntity
import kotlinx.android.synthetic.main.cardviewlibro.view.*

class LibroAdapter internal constructor(context: Context, val clickBoton: (LibroEntity) -> Unit, val clickListenerViewHolder: (LibroEntity)-> Unit): RecyclerView.Adapter<LibroAdapter.ViewHolder>() {


     private var libros: List<LibroEntity> = emptyList()

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroAdapter.ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.cardviewlibro, parent, false)
         return ViewHolder(view)
     }

     override fun getItemCount(): Int {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(libros[position], clickBoton, clickListenerViewHolder)

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         fun bind(libroentity: LibroEntity, clickBoton: (LibroEntity)->Unit, clickListenerViewHolder: (LibroEntity) -> Unit) = with(itemView){
             Glide.with(itemView.context)
                 .load(libroentity.caratula)
                 .placeholder(R.drawable.ic_launcher_background)
                 .into(Iv_book_portada)
             tvlibronombre.text = libroentity.titulo

             setFavorite.setOnClickListener {
                 clickBoton(libroentity)

             }
             this.setOnClickListener(){
                 clickListenerViewHolder(libroentity)
             }

         }
     }

 }