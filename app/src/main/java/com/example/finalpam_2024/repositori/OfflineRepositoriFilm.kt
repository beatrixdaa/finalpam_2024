package com.example.finalpam_2024.repositori

import com.example.finalpam_2024.data.Film
import com.example.finalpam_2024.data.FilmDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriFilm(private val filmDao: FilmDao):RepositoriFilm {

    override fun getAllFilmStream(): Flow<List<Film>> = filmDao.getAllFilm()

    override fun getFilmStream(id: Int): Flow<Film?> = filmDao.getFilm(id)

    override suspend fun insertFilm(film: Film) = filmDao.insert(film)

    override suspend fun deleteFilm(film: Film) = filmDao.delete(film)

    override suspend fun updateFilm(film: Film) = filmDao.update(film)
}