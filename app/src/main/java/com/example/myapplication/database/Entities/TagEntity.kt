package com.example.myapplication.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_table")
data class TagEntity (
    @PrimaryKey @ColumnInfo(name = "id_tag") val id_tag: Int,
    @ColumnInfo(name = "nombre_tag") val nombre_tag: String
)