package com.example.myapplication.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repository.AutorRepository
import com.example.myapplication.Repository.LibroRepository
import com.example.myapplication.Repository.TagRepository
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.DAO.AutorDAO
import com.example.myapplication.database.DAO.LibroDAO
import com.example.myapplication.database.DAO.TagDAO
import com.example.myapplication.database.Entities.AutorEntity
import com.example.myapplication.database.Entities.LibroEntity
import com.example.myapplication.database.Entities.TagEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibroViewModel(application: Application): AndroidViewModel(application) {
    private val repository: LibroRepository
    val allBooks: LiveData<List<LibroEntity>>

    init {
        val booksDao = AppDatabase.getDatabase(application, viewModelScope).libroDao()
        val autorDAO = AppDatabase.getDatabase(application, viewModelScope).autorDao()
        val tagDao = AppDatabase.getDatabase(application, viewModelScope).tagDao()
        repository = LibroRepository(booksDao, autorDAO, tagDao)
        allBooks = repository.allLibros
    }

    fun insert(libroEntity: LibroEntity, autorEntity: AutorEntity, tagEntity: TagEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(libroEntity, autorEntity, tagEntity)
    }

    fun getBookByTitle(name: String): LiveData<List<LibroEntity>> = repository.getBooksByTitle(name)

    fun getAll():LiveData<List<LibroEntity>> = repository.getAllfromRoomDB()

}