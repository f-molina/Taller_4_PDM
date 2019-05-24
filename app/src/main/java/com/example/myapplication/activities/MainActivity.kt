package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.ViewModels.LibroViewModel

class MainActivity : AppCompatActivity() {
    lateinit var LibroViewModel: ViewModel
    private var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
