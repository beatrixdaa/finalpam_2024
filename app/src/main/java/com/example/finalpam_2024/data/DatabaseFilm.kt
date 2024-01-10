package com.example.finalpam_2024.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Film::class, Member::class], version = 2, exportSchema = false)
abstract class DatabaseFilm : RoomDatabase() {

    abstract fun FilmDao() : FilmDao

    abstract fun MemberDao() : MemberDao

    companion object{
        @Volatile
        private var INSTANCE: DatabaseFilm? = null

        fun getDatabase(context: Context): DatabaseFilm{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseFilm::class.java, "database_film"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

