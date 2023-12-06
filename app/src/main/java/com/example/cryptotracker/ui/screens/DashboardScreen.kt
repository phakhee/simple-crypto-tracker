package com.example.cryptotracker.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cryptotracker.ui.composables.ActivityHeader
import com.example.cryptotracker.ui.composables.DashboardContainer

@Composable
fun DashboardScreen() {
    DashboardContainer {
        ActivityHeader(text = "Dashboard")
    }
}