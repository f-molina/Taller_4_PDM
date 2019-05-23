package com.example.myapplication.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "autor_table")
data class AutorEntity(
    @PrimaryKey @ColumnInfo(name = "id_autor") val id_autor: Int,
    @ColumnInfo(name = "nombre_autor") val nombre_autor: String?
)