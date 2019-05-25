package com.example.myapplication.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.database.Entities.AutorEntity

@Dao
interface AutorDAO{

    @Insert
    suspend fun insertAutor(Autor: AutorEntity)

    @Query("SELECT * FROM autor_table")
    fun getAutores(): LiveData<List<AutorEntity>>

    @Query("DELETE FROM autor_table")
    fun deleteAll()

    //Terminar el buscar por ID
   /* @Query("SELECT * FROM autor_table ")
    fun getAutor(): AutorEntity
*/
}