package com.example.finalpam_2024.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.finalpam_2024.R

import com.example.finalpam_2024.navigasi.DestinasiNavigasi
import com.google.firebase.auth.FirebaseAuth


object DestinasiLogin: DestinasiNavigasi {
    override val route= "login"
    override val titleRes= R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToHome: ()-> Unit,
    navigateToSignUp: ()-> Unit
){
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text(stringResource(R.string.username)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text(stringResource(R.string.password))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(emailState.value, passwordState.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navigateToHome()
                            } else {
                                Toast.makeText(context, task.exception?.message, Toast.LENGTH_LONG).show()
                            }
                        }
                },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = stringResource(R.string.login))
            }
                Button(
                    onClick = navigateToSignUp,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text ="SIGN UP")
                }
                }
            Spacer(modifier = Modifier.height(10.dp))
        Text(
            text ="belum ada akun? sign up dulu",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Yellow

        )
        }

    }
