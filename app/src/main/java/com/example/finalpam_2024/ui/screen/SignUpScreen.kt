package com.example.finalpam_2024.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam_2024.navigasi.DestinasiNavigasi
import com.example.finalpam_2024.ui.model.SignUpViewModel
import kotlinx.coroutines.launch

object DestinasiSignUp: DestinasiNavigasi {
    override val route: "signUp"
    override val titleRes:

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navigateToLogin: () -> Unit) {
    val context = LocalContext.current
    val signUpViewModel: SignUpViewModel = viewModel()
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource("Sign Up"),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Yellow
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it},
            label = { Text(stringResource("Email")) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text(stringResource("Password")) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        )
        Button(
            onClick = {
                coroutineScope.launch {
                    val signUpSuccessful = signUpViewModel.signUpUser(emailState.value, passwordState.value)
                    if (signUpSuccessful) {
                        navigateToLogin()
                    } else {
                        Toast.makeText(context, "Sign-up failed", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)

        )  {
            Text(stringResource("Sign Up"))


        }


    }
}