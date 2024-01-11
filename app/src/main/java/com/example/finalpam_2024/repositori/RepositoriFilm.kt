package com.example.finalpam_2024.repositori

import com.example.finalpam_2024.data.Film
import kotlinx.coroutines.flow.Flow


interface RepositoriFilm {

    fun getAllFilmStream(): Flow<List<Film>>

    fun getFilmStream(id: Int): Flow<Film?>

    suspend fun insertFilm(film: Film)

    suspend fun deleteFilm(film: Film)

    suspend fun updateFilm(film: Film)

}