package com.example.finalpam_2024.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finalpam_2024.data.Film
import com.example.finalpam_2024.repositori.RepositoriFilm

class EntryViewModel(private val repositoriFilm: RepositoriFilm): ViewModel(){

    var uiStateFilm by mutableStateOf(UIStateFilm())
        private set

    private fun validasiInput(uiState:DetailFilm=uiStateFilm.detailFilm):Boolean {
        return with(uiState) {
            judul.isNotBlank() &&genre.isNotBlank() && tahun_rilis.isNotBlank()
        }
    }
    fun updateUiState(detailFilm:DetailFilm){
        uiStateFilm=
            UIStateFilm(detailFilm,isEntryValid = validasiInput(detailFilm))
    }
    suspend fun saveFilm() {
        if (validasiInput()) {
            repositoriFilm.insertFilm(uiStateFilm.detailFilm.toFilm())
        }
    }
}

data class  UIStateFilm(
    val detailFilm: DetailFilm = DetailFilm(),
    val isEntryValid: Boolean = false
)
data class DetailFilm(
    val id: Int = 0,
    val judul:String = "",
    val genre: String = "",
    val tahun_rilis : String = "",
)
fun DetailFilm.toFilm(): Film=Film(
    id=id,
    judul=judul,
   genre=genre,
    tahun_rilis=tahun_rilis
)
fun Film.toUiStateFilm(isEntryValid: Boolean = false): UIStateFilm = UIStateFilm(
    detailFilm=this.toDetailFilm(),
    isEntryValid=isEntryValid
)
fun Film.toDetailFilm(): DetailFilm = DetailFilm(
    id=id,
    judul=judul,
   genre=genre,
   tahun_rilis =tahun_rilis
)