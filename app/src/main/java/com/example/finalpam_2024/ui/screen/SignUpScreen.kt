package com.example.finalpam_2024.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam_2024.navigasi.DestinasiNavigasi
import com.example.finalpam_2024.ui.model.SignUpViewModel

object DestinasiSignUp: DestinasiNavigasi {
    override val route= "signup"
    override val titleRes= "Fixinema"
}

@Composable
fun SignUpScreen(navigateToLogin: () -> Unit) {
    val context = LocalContext.current
    val signUpViewModel: SignUpViewModel = viewModel()
    val emailState =
}