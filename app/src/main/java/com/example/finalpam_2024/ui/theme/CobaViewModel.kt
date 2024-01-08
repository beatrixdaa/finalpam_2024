package com.example.finalpam_2024.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finalpam_2024.ui.theme.data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel : ViewModel() {
    var namaUsr: String by mutableStateOf("")
        private set
    var noTelp: String by mutableStateOf("")
        private set
    var email: String by mutableStateOf("")
        private set
    var password: String by mutableStateOf("")
        private set

    var alamat: String by mutableStateOf("")
        private set
    var jenisKl: String by mutableStateOf("")
        private set


    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> get() = _uiState

    fun insertData(nm: String, tlp: String, almt: String, jk: String, emal: String, pswd: String) {
        namaUsr = nm
        noTelp = tlp
        alamat = almt
        email = emal
        jenisKl = jk
        password = pswd


    }


    fun setJenisK(pilihJK: String) {
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }


}

