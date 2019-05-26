package com.example.myapplication.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.database.Entities.LibroEntity
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.Fragment
import com.example.myapplication.fragments.MainContentFragment
import com.example.myapplication.fragments.MainListFragment

class MainActivity : AppCompatActivity(), MainListFragment.ClickedMovieListener {

    private lateinit var mainFragment: MainListFragment
    private lateinit var mainContentFragment: MainContentFragment
    private var resource = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarmain)

        initFragments()
    }

    fun initFragments() {
        mainFragment = MainListFragment()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            resource = R.id.portrait_main_place_holder
            changeFragment(resource, mainFragment)
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            resource = R.id.land_main_place_holder
            changeFragment(resource, mainFragment)
        }

    }

    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).addToBackStack(null).commit()
    }

    private fun showContent(id_placeholder: Int, movie: LibroEntity) {
        mainContentFragment = MainContentFragment.newInstance(movie)
        changeFragment(id_placeholder, mainContentFragment)
    }

    override fun managePortraitItemClick(libro: LibroEntity) = showContent(R.id.portrait_main_place_holder, libro)

    override fun managedLandscapeItemClick(libro: LibroEntity) = showContent(R.id.land_main_place_holder, libro)
}
