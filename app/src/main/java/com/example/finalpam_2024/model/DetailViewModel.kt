package com.example.finalpam_2024.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam_2024.halaman.DetailsDestination
import com.example.finalpam_2024.repositori.RepositoriFilm
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriFilm: RepositoriFilm
) : ViewModel(){
    private val FilmId: Int = checkNotNull(savedStateHandle[DetailsDestination.FilmIdArg])
    val uiState: StateFlow<ItemDetailUiState> =
        repositoriFilm.getFilmStream(FilmId)
            .filterNotNull()
            .map { ItemDetailUiState(detailFilm = it.toDetailFilm())
            }.stateIn(
                scope =viewModelScope ,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailUiState()
            )

    suspend fun deleteItem() {
        repositoriFilm.deleteFilm(uiState.value.detailFilm.toFilm())
    }
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


data class ItemDetailUiState (
    val outOfStock: Boolean = true,
    val detailFilm: DetailFilm = DetailFilm(),
)