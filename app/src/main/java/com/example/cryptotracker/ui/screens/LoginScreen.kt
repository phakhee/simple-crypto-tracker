package com.example.cryptotracker.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cryptotracker.ui.composables.ActivityHeader
import com.example.cryptotracker.ui.composables.AuthenticationTextField
import com.example.cryptotracker.ui.composables.ButtonDivider
import com.example.cryptotracker.ui.composables.ColumnSpacer
import com.example.cryptotracker.ui.composables.ForgotPasswordButton
import com.example.cryptotracker.ui.composables.LoginContainer
import com.example.cryptotracker.ui.composables.FormContainer
import com.example.cryptotracker.ui.composables.LoginButton
import com.example.cryptotracker.ui.composables.SignUpButton

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun login() {

    }

    fun signUp() {

    }

    fun forgotPassword() {

    }

    LoginContainer {
        ColumnSpacer()
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
        ButtonDivider(text = "or")
        SignUpButton(value = "Sign Up", onClick = { signUp() })

        ColumnSpacer()
        ForgotPasswordButton(value = "Forgot Password", onClick = { forgotPassword() })
    }
}