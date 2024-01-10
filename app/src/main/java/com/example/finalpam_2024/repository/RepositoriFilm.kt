package com.example.finalpam_2024.repository

import com.example.finalpam_2024.data.Film
import kotlinx.coroutines.flow.Flow

interface RepositoriFilm {
    fun getAllFilmStream(): Flow<List<Film>>
    fun getFilmStream(id: Int): Flow<Film?>
    suspend fun insertFilm(Film: Film)
    suspend fun deleeteFilm(Film: Film)
    suspend fun updateFilm(Film: Film)
}