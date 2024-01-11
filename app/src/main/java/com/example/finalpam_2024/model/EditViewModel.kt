package com.example.finalpam_2024.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam_2024.halaman.ItemEditDestination
import com.example.finalpam_2024.repositori.RepositoriFilm
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHadle: SavedStateHandle,
    private val repositoriFilm: RepositoriFilm
) : ViewModel() {
    var FilmUiState by mutableStateOf(UIStateFilm())
        private set
    private val itemId: Int = checkNotNull(savedStateHadle[ItemEditDestination.itemIdArg])
    init {
        viewModelScope.launch {
            FilmUiState = repositoriFilm.getFilmStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateFilm(true)
        }
    }
    suspend fun updateFilm () {
        if (validasiInput(FilmUiState.detailFilm)) {
            repositoriFilm.updateFilm(FilmUiState.detailFilm.toFilm())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState (detailFilm: DetailFilm) {
        FilmUiState = UIStateFilm(detailFilm = detailFilm, isEntryValid = validasiInput(detailFilm))
    }

    private fun validasiInput(uiState: DetailFilm = FilmUiState.detailFilm ): Boolean {
        return with(uiState) {
            judul.isNotBlank() && genre.isNotBlank() && tahun_rilis.isNotBlank()
        }
    }
}


