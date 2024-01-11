package com.example.finalpam_2024.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam_2024.data.Film
import com.example.finalpam_2024.repositori.RepositoriFilm
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repositoriFilm: RepositoriFilm): ViewModel(){
    companion object {
        private const val  TIMEOUT_MILLIS = 10_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriFilm.getAllFilmStream()
        .filterNotNull()
        .map { HomeUiState(listFilm = it.toList()) }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue=HomeUiState()
        )
    data class HomeUiState(
        val listFilm: List<Film> = listOf()
    )
}