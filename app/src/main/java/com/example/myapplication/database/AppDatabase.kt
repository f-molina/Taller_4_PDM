package com.example.myapplication.database

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.database.DAO.AutorDAO
import com.example.myapplication.database.DAO.LibroDAO
import com.example.myapplication.database.DAO.TagDAO
import com.example.myapplication.database.Entities.AutorEntity
import com.example.myapplication.database.Entities.LibroEntity
import com.example.myapplication.database.Entities.TagEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.AccessControlContext

@Database(entities = [LibroEntity::class, AutorEntity::class, TagEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun libroDao(): LibroDAO
    abstract fun autorDao(): AutorDAO
    abstract fun tagDao(): TagDAO


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Book_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }


    private class DatabseCall(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { appDatabase ->
                scope.launch(Dispatchers.IO) {
                    LlenarDB(appDatabase.libroDao(), appDatabase.autorDao(), appDatabase.tagDao())
                }
            }
        }


        suspend fun LlenarDB(libroDAO: LibroDAO, autorDAO: AutorDAO, tagDAO: TagDAO) {

            var autor = AutorEntity(1, "Danniela Renderos")
            autorDAO.insertAutor(autor)
            autor = AutorEntity(2, "Fatima Renderos")
            autorDAO.insertAutor(autor)

            var tag = TagEntity(1, "Romance")
            tagDAO.insert_tag(tag)
            tag = TagEntity(2, "Ficcion")
            tagDAO.insert_tag(tag)


            var libro = LibroEntity(
                1, "La biblia", "caratula", 2, "La casa",
                123, "Historia de Dios", 1, 10, 1
            )
            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                2, "Sherlock Holmes", "caratula", 1, "La casa",
                1255, "Historia de detectives", 2, 1, 1
            )
            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                3, "GOT", "caratula", 1, "La casa",
                1255, "Historia de guerra y dragones", 2, 3, 0
            )
            libroDAO.insertLibro(libro)


        }
    }
}




