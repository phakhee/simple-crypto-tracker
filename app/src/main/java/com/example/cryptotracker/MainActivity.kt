package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.cryptotracker.ui.composables.ActivityHeader
import com.example.cryptotracker.ui.composables.AuthenticationTextField
import com.example.cryptotracker.ui.composables.ColumnCenteredContent
import com.example.cryptotracker.ui.theme.CryptoTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            CryptoTrackerTheme {
                ColumnCenteredContent {
                    ActivityHeader(text = "Simple Crypto Tracker")

                    AuthenticationTextField(
                        value=email,
                        onValueChange = { email = it },
                        label="E-mail"
                    )

                    AuthenticationTextField(
                        value=password,
                        onValueChange = { password = it },
                        label="Password"
                    )
                }
            }
        }
    }
}
