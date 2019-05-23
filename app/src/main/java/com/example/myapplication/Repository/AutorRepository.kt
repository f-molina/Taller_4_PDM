package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.database.DAO.AutorDAO
import com.example.myapplication.database.Entities.AutorEntity

class AutorRepository(private val autorDao: AutorDAO) {

    val allAutores: LiveData<List<AutorEntity>> = autorDao.getAutores()

    @WorkerThread
    suspend fun insert(autor: AutorEntity) {
        autorDao.insertAutor(autor)
    }
}