package com.example.myapplication.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.LibroAdapter
import com.example.myapplication.R
import com.example.myapplication.ViewModels.LibroViewModel
import com.example.myapplication.database.Entities.AutorEntity
import com.example.myapplication.database.Entities.LibroEntity
import com.example.myapplication.database.Entities.TagEntity
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.fragments.MainContentFragment
import com.example.myapplication.fragments.MainListFragment

class MainActivity : AppCompatActivity(), MainListFragment.ClickedMovieListener  {

    private lateinit var mainFragment: MainListFragment
    private lateinit var mainContentFragment: MainContentFragment
    private var resource = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarmain)

        initFragments()
    }

    fun initFragments(){
        mainFragment = MainListFragment()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            resource = R.id.portrait_main_place_holder
        }

        changeFragment(resource, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    private fun showContent(id_placeholder: Int, movie: LibroEntity) {
        mainContentFragment = MainContentFragment.newInstance(movie)
        changeFragment(id_placeholder, mainContentFragment)
    }

    override fun managePortraitItemClick(movie: LibroEntity) = showContent(R.id.portrait_main_place_holder, movie)

    override fun managedLandscapeItemClick(movie: LibroEntity) = showContent(R.id.portrait_main_place_holder, movie)
}
