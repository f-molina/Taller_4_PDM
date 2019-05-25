package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.database.DAO.AutorDAO
import com.example.myapplication.database.DAO.LibroDAO
import com.example.myapplication.database.DAO.TagDAO
import com.example.myapplication.database.Entities.AutorEntity
import com.example.myapplication.database.Entities.LibroEntity
import com.example.myapplication.database.Entities.TagEntity

class LibroRepository(private val libroDao: LibroDAO, private val autorDAO: AutorDAO, private val tagDAO: TagDAO) {

    val allLibros: LiveData<List<LibroEntity>> = libroDao.getLibros()

    val allFavs: LiveData<List<LibroEntity>> = libroDao.getFavs()

    @WorkerThread
    suspend fun insert(libro: LibroEntity, autor: AutorEntity, tag: TagEntity) {
        libroDao.insertLibro(libro)
        autorDAO.insertAutor(autor)
        tagDAO.insert_tag(tag)
    }

    fun getBooksByTitle(name: String) = libroDao.getLibroByTitle(name)

    fun getAllfromRoomDB():LiveData<List<LibroEntity>> = libroDao.getLibros()
}