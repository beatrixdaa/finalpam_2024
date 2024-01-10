package com.example.finalpam_2024.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Film::class, Member::class],
    version = 2,
    exportSchema = false)
    abstract class DatabaseFilm : RoomDatabase() {

    abstract fun FilmDao() : FilmDao

    abstract fun MemberDao() : MemberDao




    companion object {
        @Volatile
        private var Instance: DatabaseFilm? = null
        fun getDatabase(context: Context): DatabaseFilm{
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context,DatabaseFilm::class.java,"Film_database")
                    .build().also{ Instance=it}
            })
        }
    }
}

