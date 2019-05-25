package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.database.Entities.LibroEntity
import kotlinx.android.synthetic.main.book_viewer.view.*

class MainContentFragment: Fragment() {

    var book = LibroEntity()

    companion object {
        fun newInstance(dataset: LibroEntity): MainContentFragment {
            return MainContentFragment().apply {
                book = dataset
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.book_viewer, container, false)

        view.collapsingtoolbarviewer.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        view.collapsingtoolbarviewer.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)


        bindData(view, book)

        return view
    }

    fun bindData(view: View, data: LibroEntity){
        Glide.with(this)
            .load(data.caratula)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.app_bar_image_viewer)

        view.collapsingtoolbarviewer.title = data.titulo
        view.resumen_viewer.text = data.resumen
        view.autor_tv.text = data.editorial
        view.actors_viewer.text = data.isbn
        view.genre_viewer.text = data.editorial
        view.released_viewer.text = data.editorial
        view.app_bar_id_viewer.text = data.titulo
    }
}