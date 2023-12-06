package com.example.cryptotracker.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.cryptotracker.ui.classes.Crypto
import com.example.cryptotracker.ui.composables.ActivityHeader
import com.example.cryptotracker.ui.composables.DashboardContainer
import com.example.cryptotracker.ui.composables.MainText
import com.example.cryptotracker.ui.util.cryptoClient
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DashboardScreen() {
    val (allCrypto, setAllCrypto) = remember { mutableStateOf<List<Crypto>>(mutableListOf()) }
    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        setAllCrypto(cryptoClient.retrieveAllCrypto())
    }

    DashboardContainer {
        ActivityHeader(text = "Dashboard")

        MainText(text = if (allCrypto.isEmpty()) "loading" else "done")
    }
}