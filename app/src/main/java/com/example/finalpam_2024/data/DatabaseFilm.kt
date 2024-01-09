package com.example.finalpam_2024.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Film::class, Member::class], version = 2, exportSchema = false)
abstract class DatabaseFilm : RoomDatabase() {

    abstract fun FilmDao() : FilmDao

    abstract fun MemberDao() : MemberDao

}

