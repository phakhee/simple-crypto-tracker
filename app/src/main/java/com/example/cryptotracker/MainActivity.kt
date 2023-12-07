package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptotracker.ui.composables.AppBox
import com.example.cryptotracker.ui.screens.DashboardScreen
import com.example.cryptotracker.ui.screens.LoginScreen
import com.example.cryptotracker.ui.theme.CryptoTrackerTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTrackerTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val user by remember { mutableStateOf(Firebase.auth.currentUser) }

    AppBox {
        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable(route = "login") {
                if (user == null) LoginScreen(navController = navController)
                else DashboardScreen()
            }
            composable(route = "dashboard") { DashboardScreen() }
        }
    }
}