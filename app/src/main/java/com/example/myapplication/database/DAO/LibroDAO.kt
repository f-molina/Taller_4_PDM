package com.example.myapplication.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.database.Entities.LibroEntity

@Dao
interface LibroDAO {


    @Insert
    suspend fun insertLibro(Libro: LibroEntity)

    @Query("SELECT * FROM libro_table")
    fun getLibros(): LiveData<List<LibroEntity>>

    @Query("SELECT * FROM libro_table WHERE favorito = 1 ")
    fun getFavs(): LiveData<List<LibroEntity>>


}