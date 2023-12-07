package com.example.cryptotracker.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.cryptotracker.ui.classes.Crypto
import com.example.cryptotracker.ui.components.DataOverview
import com.example.cryptotracker.ui.composables.MainHeader
import com.example.cryptotracker.ui.composables.CenteredContent
import com.example.cryptotracker.ui.composables.CryptoDropdownItem
import com.example.cryptotracker.ui.composables.CryptoDropdownMenu
import com.example.cryptotracker.ui.composables.DashboardContainer
import com.example.cryptotracker.ui.composables.LoadingIndicator
import com.example.cryptotracker.ui.composables.StyledTextField
import com.example.cryptotracker.ui.composables.SubText
import com.example.cryptotracker.ui.theme.NeonGreen
import com.example.cryptotracker.ui.theme.TextColor
import com.example.cryptotracker.ui.util.cryptoClient
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DashboardScreen() {
    val coroutineScope = rememberCoroutineScope()
    var allCrypto by remember { mutableStateOf<List<Crypto>>(mutableListOf()) }
    var cryptoName by remember { mutableStateOf("") }
    val selectedCrypto: MutableState<Crypto?> = remember { mutableStateOf(null) }

    coroutineScope.launch {
        allCrypto = cryptoClient.retrieveAllCrypto()
    }

    fun onCryptoItemClick(crypto: Crypto) {
        selectedCrypto.value = crypto
        cryptoName = ""
    }

    DashboardContainer {
        if (selectedCrypto.value === null) MainHeader(text = "Crypto Statistics")

        if (allCrypto.isEmpty()) LoadingIndicator(text = "Loading crypto data")
        else {
            StyledTextField(
                label = "Search crypto by name, e.g. Bitcoin",
                value = cryptoName,
                onValueChange = { cryptoName = it },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon for searching crypto",
                        tint = TextColor
                    )
                }
            )

            if (cryptoName.length >= 2) {
                CryptoDropdownMenu(expanded = true) {
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
            }

            if (selectedCrypto.value === null) CenteredContent {
                SubText(text = "Selected crypto data will be displayed here")
            }
            else DataOverview(crypto = selectedCrypto.value!!)
        }
    }
}