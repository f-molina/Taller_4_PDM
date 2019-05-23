package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.database.DAO.TagDAO
import com.example.myapplication.database.Entities.TagEntity

class TagRepository(private val tagDAO: TagDAO) {

    val allLibros: LiveData<List<TagEntity>> = tagDAO.getTags()

    @WorkerThread
    suspend fun insert(tag: TagEntity) {
        tagDAO.insert_tag(tag)
    }
}