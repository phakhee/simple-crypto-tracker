package com.example.cryptotracker.ui.util

import androidx.navigation.NavController
import java.text.NumberFormat
import java.util.Locale

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailRegex.matches(email) && email.isNotEmpty()
}

fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route)
}

fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()

fun formatAmericanDouble(value: Number): String {
    val usFormat = NumberFormat.getInstance(Locale.US)
    return usFormat.format(value)
}