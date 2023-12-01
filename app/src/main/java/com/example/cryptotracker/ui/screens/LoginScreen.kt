package com.example.cryptotracker.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.cryptotracker.ui.composables.ActivityHeader
import com.example.cryptotracker.ui.composables.AuthenticationTextField
import com.example.cryptotracker.ui.composables.LoginContainer
import com.example.cryptotracker.ui.composables.FormContainer
import com.example.cryptotracker.ui.composables.LoginButton

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun login() {

    }

    LoginContainer {
        ActivityHeader(text = "Simple Crypto Tracker")

        FormContainer {
            AuthenticationTextField(
                value = email,
                onValueChange = { email = it },
                label = "E-mail"
            )

            AuthenticationTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                type = "password"
            )
        }
        LoginButton(value = "Login", onClick = { login() })
    }
}