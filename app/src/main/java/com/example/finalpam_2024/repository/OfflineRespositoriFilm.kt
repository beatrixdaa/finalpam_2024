package com.example.finalpam_2024.repository

import com.example.finalpam_2024.data.Film
import com.example.finalpam_2024.data.FilmDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriFilm(private val FilmDao: FilmDao):RepositoriFilm {
    override fun getAllFilmStream(): Flow<List<Film>> {
        return FilmDao.getAllFilm()
    }

    override fun getFilmStream(id: Int): Flow<Film?> {
        return FilmDao.getFilm(id)

    }

    override suspend fun insertFilm(Film: Film) {
        return FilmDao.insert(Film)
    }

    override suspend fun deleeteFilm(Film: Film) {
        return FilmDao.delete(Film)
    }

    override suspend fun updateFilm(Film: Film) {
        return FilmDao.update(Film)
    }

}