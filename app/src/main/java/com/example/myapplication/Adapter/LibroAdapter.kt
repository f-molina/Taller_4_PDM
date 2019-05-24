package com.example.myapplication.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.Entities.LibroEntity
import java.security.AccessControlContext

 class LibroAdapter internal constructor(context: Context, val clickListenerBoton: (LibroEntity) -> Unit, val clickListenerViewHolder: (LibroEntity)-> Unit): RecyclerView.Adapter<LibroAdapter.ViewHolder>() {
     private var libros: List<LibroEntity> = emptyList()

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroAdapter.ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.cardviewlibro, parent, false)
         return RecyclerView.ViewHolder(view)
     }

     override fun getItemCount(): Int {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }

     override fun onBindViewHolder(holder: LibroAdapter.ViewHolder, position: Int) {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }

 }