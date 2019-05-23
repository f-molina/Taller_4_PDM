package com.example.myapplication.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libro_table")
data class LibroEntity(
    @PrimaryKey @ColumnInfo(name = "id_libro") val id_libro: Int,
    @ColumnInfo(name = "titulo") val titulo: String?,
    @ColumnInfo(name = "caratula") val caratula: String?,
    @ColumnInfo(name = "id_autores") val id_autores: Int?,
    @ColumnInfo(name = "editorial") val editorial: String?,
    @ColumnInfo(name = "isbn") val isbn: Int?,
    @ColumnInfo(name = "resumen") val resumen: String?,
    @ColumnInfo(name = "id_tag") val id_tag: Int?,
    @ColumnInfo(name = "edicion") val edicion: Int?,
    @ColumnInfo(name = "favorito") val favorito: Int?


)