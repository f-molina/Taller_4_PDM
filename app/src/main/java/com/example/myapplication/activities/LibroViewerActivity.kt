package com.example.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.myapplication.R
import com.example.myapplication.database.Entities.LibroEntity
import kotlinx.android.synthetic.main.activity_libro_vw.*
import kotlinx.android.synthetic.main.cardviewlibro.*

class LibroViewerActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_vw)

        val lbD: LibroEntity? =  intent.extras.getParcelable("id_libro")
        if(lbD!=null){
            init(lbD)
        }

    }
    fun init(libro: LibroEntity){
        Glide.with(this)
            .load(libro.caratula)
            .placeholder(R.drawable.ic_launcher_background)
            .into(libroportada)
        titulotv.text = libro.titulo
        autortv.text = libro.id_autores
        ediciontv.text = libro.edicion.toString()
        editorialtv.text = libro.editorial
        isbntv.text = libro.isbn
        resumentv.text = libro.resumen
    }
}