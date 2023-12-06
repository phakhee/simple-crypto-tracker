package com.example.cryptotracker.ui.util

import androidx.navigation.NavController

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailRegex.matches(email) && email.isNotEmpty()
}

fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route)
}