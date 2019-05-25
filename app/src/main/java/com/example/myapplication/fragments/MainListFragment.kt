package com.example.myapplication.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.LibroAdapter
import com.example.myapplication.R
import com.example.myapplication.ViewModels.LibroViewModel
import com.example.myapplication.database.Entities.LibroEntity
import java.lang.RuntimeException
import androidx.lifecycle.Observer
import com.example.myapplication.constants.AppConstants
import kotlinx.android.synthetic.main.books_list_fragment.view.*

class MainListFragment: Fragment() {

    private lateinit var movieViewModel: LibroViewModel
    private lateinit var moviesAdapter: LibroAdapter
    var listenerTool : ClickedMovieListener? = null

    interface ClickedMovieListener{
        fun managePortraitItemClick(movie: LibroEntity)
        fun managedLandscapeItemClick(movie: LibroEntity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickedMovieListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de la interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.books_list_fragment, container, false)

        movieViewModel = ViewModelProviders.of(this).get(LibroViewModel::class.java)

        initRecyclerView(resources.configuration.orientation, view)

        movieViewModel.getAll().observe(this, Observer { result ->
            moviesAdapter.changeDataSet(result)
        })

        return view
    }

    fun initRecyclerView(orientation: Int, container: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerview  = container.rv_list

        moviesAdapter = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            LibroAdapter(movies = AppConstants.emptymovies, clickListener = { movie: LibroEntity -> listenerTool?.managePortraitItemClick(movie)})
        }else {
            LibroAdapter(movies = AppConstants.emptymovies, clickListener = { movie: LibroEntity -> listenerTool?.managedLandscapeItemClick(movie)})
        }

        recyclerview.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Hace que cuando presiones el botón de sumbit se ejecute lo que pongas aquí
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Hace que cambie dinamicamente mientras escribis, porque ejecuta lo que pongas aquí cada vez que escribis.
                queryToDatabase(newText?: "N/A")
                return true
            }

        })
    }

    private fun queryToDatabase(query: String) = movieViewModel.getBookByTitle("%$query%").observe(this,
        Observer { queryResult -> moviesAdapter.changeDataSet(queryResult)})
}
