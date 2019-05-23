package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.database.DAO.LibroDAO
import com.example.myapplication.database.Entities.LibroEntity

class LibroRepository(private val libroDao: LibroDAO) {

    val allLibros: LiveData<List<LibroEntity>> = libroDao.getLibros()

    val allFavs: LiveData<List<LibroEntity>> = libroDao.getFavs()

    @WorkerThread
    suspend fun insert(libro: LibroEntity) {
        libroDao.insertLibro(libro)
    }

}