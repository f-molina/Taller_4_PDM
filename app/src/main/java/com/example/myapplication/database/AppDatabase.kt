package com.example.myapplication.database

import android.content.Context
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

@Database(entities = [LibroEntity::class, AutorEntity::class, TagEntity::class], version = 8, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun libroDao(): LibroDAO
    abstract fun autorDao(): AutorDAO
    abstract fun tagDao(): TagDAO


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Book_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabseCall(scope))
                    .build()
                INSTANCE = instance
                instance
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
                "1", "La biblia", "caratula", "La casa",
                "123", "Historia de Dios", "1", "10", true
            )
            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                "2", "Sherlock Holmes", "caratula", "La casa",
                "1255", "Historia de detectives", "2", "1", false
            )
            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                "3", "GOT", "caratula", "La casa",
                "1224", "Historia de guerra y dragones", "4", "56", true
            )
            libroDAO.insertLibro(libro)
        }
    }
}




