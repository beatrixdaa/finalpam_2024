package com.example.finalpam_2024.ui.model

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class SignUpViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signUpUser(email: String, password: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            true // Sign-up berhasil
        } catch (e: Exception) {
            false // Sign-up gagal
        }
    }

}