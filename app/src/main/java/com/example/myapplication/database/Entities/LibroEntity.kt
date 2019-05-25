package com.example.myapplication.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libro_table")
data class LibroEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_libro") val id_libro: String = "N/a",
    @ColumnInfo(name = "titulo") val titulo: String = "N/a",
    @ColumnInfo(name = "caratula") val caratula: String = "N/a",
    @ColumnInfo(name = "editorial") val editorial: String = "N/a",
    @ColumnInfo(name = "isbn") val isbn: String = "N/a",
    @ColumnInfo(name = "resumen") val resumen: String? = "N/a",
    @ColumnInfo(name = "id_tag") val id_tag: String = "N/a",
    @ColumnInfo(name = "edicion") val edicion: String = "N/a",
    @ColumnInfo(name = "favorito") val favorito: Boolean = true
)