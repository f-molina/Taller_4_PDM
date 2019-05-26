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

@Database(entities = [LibroEntity::class, AutorEntity::class, TagEntity::class], version = 12, exportSchema = false)
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
                "1", "La biblia", "https://porfirioflores.org/wp-content/uploads/2018/01/biblia.jpg", "La casa",
                "123", "Historia de Dios", "1", "10", true
            )
            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                "2", "Sherlock Holmes", "https://dg9aaz8jl1ktt.cloudfront.net/the_files/46589/std.jpg?1452597915", "La edi",
                "1255", "Historia de detectives", "2", "1", false
            )
            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                "3", "GOT", "https://www.thomann.de/pics/bdb/439871/13137706_800.jpg", "Satori",
                "1224", "Historia de guerra y dragones", "4", "56", true
            )
            libroDAO.insertLibro(libro)
            
            
            libro = LibroEntity(
                    "4", "El principito", "https://fotos00.laopiniondezamora.es/mmp/2018/04/06/690x278/el-principito.jpg", "Galaxia",
                    "1255", "Historia de fantasía", "2", "1", true
            )

            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                    "5", "El código Da Vinci", "https://mlstaticquic-a.akamaihd.net/libro-el-codigo-da-vinci-dan-brown-D_NQ_NP_871672-MLU29350479226_022019-F.jpg", "La casa",
                    "1255", "Historia de suspenso y misterio", "2", "0", true
            )
            libroDAO.insertLibro(libro)




            libro = LibroEntity(
                    "6", "Romeo and Juliet", "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/a18fba25660183.56348cd307ca4.jpg", "Abc",
                    "1255", "Historia de fantasía", "2", "1", true
            )

            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                    "7", "Cumbres Borrascosas", "https://c8.alamy.com/compes/kretn8/cumbres-borrascosas-kretn8.jpg", "La casa",
                    "1255", "Historia de suspenso y misterio", "2", "0", true
            )
            libroDAO.insertLibro(libro)




            libro = LibroEntity(
                    "8", "Orgullo y prejuicio", "https://i.pinimg.com/originals/a8/5b/61/a85b61b4e4387726779730b3b37b5c2e.jpg", "Siruela",
                    "1255", "Historia de suspenso y misterio", "2", "0", true
            )
            libroDAO.insertLibro(libro)



            libro = LibroEntity(
                    "9", "El alquimista", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Der_unsterbliche_Alchemyst_%28Michael_Scott%2C_2008%29.jpg/220px-Der_unsterbliche_Alchemyst_%28Michael_Scott%2C_2008%29.jpg", "Salamandra",
                    "1255", "Historia de suspenso y misterio", "2", "0", true
            )
            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                    "10", "La Odisea", "https://i.pinimg.com/236x/1b/54/4b/1b544b2c9a4b81ee1812e58bd70d4425--read-books-my-books.jpg", "La casa",
                    "1255", "Historia de suspenso y misterio", "2", "0", true
            )
            libroDAO.insertLibro(libro)


            libro = LibroEntity(
                    "11", "Percy Jackson", "https://imagessl4.casadellibro.com/a/l/t5/64/9788498386264.jpg", "Planeta",
                    "1255", "Historia de suspenso y misterio", "2", "0", true
            )
            libroDAO.insertLibro(libro)

            libro = LibroEntity(
                    "12", "Harry Potter", "https://imagessl4.casadellibro.com/a/l/t5/64/9788498386264.jpg", "Sexto sentido",
                    "1255", "Historia de suspenso y misterio", "2", "0", true
            )
            libroDAO.insertLibro(libro)


            libro = LibroEntity(
                    "13", "La heredera", "https://static.megustaleer.com.ar/images/libros_200_x/9788416306497.jpg", "Planeta",
                    "1255", "Historia de suspenso y misterio", "2", "0", true
            )
            libroDAO.insertLibro(libro)
            
        }
    }
}




