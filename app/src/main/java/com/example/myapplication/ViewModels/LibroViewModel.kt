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
import com.example.myapplication.database.Entities.LibroEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibroViewModel(application: Application): AndroidViewModel(application) {
    private val repository: LibroRepository
    private val repositoryAutor: AutorRepository
    private val repositoryTag: TagRepository
    val allBooks: LiveData<List<LibroEntity>>
    init {
        val booksDao: LibroDAO = AppDatabase.getDatabase(application, viewModelScope).libroDao()
        val autorDao: AutorDAO = AppDatabase.getDatabase(application, viewModelScope).autorDao()
        val tagDao: TagDAO = AppDatabase.getDatabase(application, viewModelScope).tagDao()
        repository = LibroRepository(booksDao)
        repositoryAutor = AutorRepository(autorDao)
        repositoryTag = TagRepository(tagDao)
        allBooks = repository.allLibros
    }

    fun insert(libroEntity: LibroEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(libroEntity)
    }
}