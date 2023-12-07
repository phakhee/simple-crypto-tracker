package com.example.cryptotracker.ui.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.cryptotracker.ui.composables.ActivityHeader
import com.example.cryptotracker.ui.composables.StyledTextField
import com.example.cryptotracker.ui.composables.ButtonDivider
import com.example.cryptotracker.ui.composables.ColumnSpacer
import com.example.cryptotracker.ui.composables.ForgotPasswordButton
import com.example.cryptotracker.ui.composables.AuthenticationContainer
import com.example.cryptotracker.ui.composables.FormContainer
import com.example.cryptotracker.ui.composables.LoginButton
import com.example.cryptotracker.ui.composables.ShowToast
import com.example.cryptotracker.ui.composables.SignUpButton
import com.example.cryptotracker.ui.util.authentication
import com.example.cryptotracker.ui.util.navigateTo

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }

    fun login() {
        authentication.login(
            email = email,
            password = password,
            onSuccess = { navigateTo(navController, "dashboard") },
            onFail = { showToast = true }
        )
    }

    fun signUp() {

    }

    fun forgotPassword() {

    }

    AuthenticationContainer {
        ColumnSpacer()
        ActivityHeader(text = "Simple Crypto Tracker")

        FormContainer {
            StyledTextField(
                value = email,
                onValueChange = { email = it },
                label = "E-mail"
            )

            StyledTextField(
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

        if (showToast) {
            ShowToast(text = "Invalid e-mail and/or password", duration = Toast.LENGTH_SHORT)
            showToast = false
        }
    }
}