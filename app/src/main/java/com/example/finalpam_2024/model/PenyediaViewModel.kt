package com.example.finalpam_2024.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalpam_2024.AplikasiFilm

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiFilm().container.repositoriFilm)
        }
        initializer {
            EntryViewModel(aplikasiFilm().container.repositoriFilm)
        }
        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                aplikasiFilm().container.repositoriFilm,
            )
        }
        initializer {
            EditViewModel (
                createSavedStateHandle(),
                aplikasiFilm().container.repositoriFilm,
            )
        }
    }
}
fun CreationExtras.aplikasiFilm(): AplikasiFilm =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiFilm)

