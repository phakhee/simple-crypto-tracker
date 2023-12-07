package com.example.cryptotracker.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.cryptotracker.ui.classes.Crypto
import com.example.cryptotracker.ui.composables.ActivityHeader
import com.example.cryptotracker.ui.composables.CenteredContent
import com.example.cryptotracker.ui.composables.CryptoDropdownIcon
import com.example.cryptotracker.ui.composables.CryptoDropdownItem
import com.example.cryptotracker.ui.composables.CryptoDropdownMenu
import com.example.cryptotracker.ui.composables.DashboardContainer
import com.example.cryptotracker.ui.composables.LoadingIndicator
import com.example.cryptotracker.ui.composables.MainText
import com.example.cryptotracker.ui.composables.StyledTextField
import com.example.cryptotracker.ui.composables.SubText
import com.example.cryptotracker.ui.util.cryptoClient
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DashboardScreen() {
    val coroutineScope = rememberCoroutineScope()
    var allCrypto by remember { mutableStateOf<List<Crypto>>(mutableListOf()) }
    var cryptoName by remember { mutableStateOf("") }

    coroutineScope.launch {
        allCrypto = cryptoClient.retrieveAllCrypto()
    }

    val onCryptoItemClick: (crypto: Crypto) -> Unit = {
        println("hoi")
    }

    DashboardContainer {
        ActivityHeader(text = "Dashboard")

        if (allCrypto.isEmpty()) LoadingIndicator(text = "Loading crypto data")
        else {
            StyledTextField(
                label = "Search crypto by name, e.g. Bitcoin",
                value = cryptoName,
                onValueChange = { cryptoName = it }
            )

            CryptoDropdownMenu(
                expanded = cryptoName.length >= 2,
            ) {
                for (crypto in allCrypto.filter { crypto ->
                    crypto.name.contains(
                        cryptoName, ignoreCase = true
                    ) || crypto.symbol.contains(
                        cryptoName, ignoreCase = true
                    )
                }) {
                    CryptoDropdownItem(
                        text = crypto.name,
                        subText = crypto.symbol,
                        onClick = { onCryptoItemClick(crypto) }
                    )
                }
            }

            CenteredContent {
                SubText(text = "Selected crypto data will be displayed here")
            }
        }
    }
}