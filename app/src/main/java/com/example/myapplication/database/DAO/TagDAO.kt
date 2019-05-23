package com.example.myapplication.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.database.Entities.TagEntity

@Dao
interface TagDAO {

    @Insert
    suspend fun insert_tag( Tag: TagEntity)

    @Query("SELECT * FROM tag_table")
     fun getTags(): LiveData<List<TagEntity>>


}