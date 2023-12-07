package com.example.cryptotracker.ui.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.cryptotracker.ui.classes.Crypto
import com.example.cryptotracker.ui.classes.CryptoQuote
import com.example.cryptotracker.ui.composables.QuoteData
import com.example.cryptotracker.ui.composables.QuoteDataName
import com.example.cryptotracker.ui.composables.QuoteDataValue
import com.example.cryptotracker.ui.composables.QuoteRow
import com.example.cryptotracker.ui.util.cryptoClient
import com.example.cryptotracker.ui.util.round
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DataOverview(crypto: Crypto) {
    val coroutineScope = rememberCoroutineScope()
    val cryptoQuote: MutableState<CryptoQuote?> = remember { mutableStateOf(null) }

    coroutineScope.launch {
        cryptoQuote.value = cryptoClient.retrieveCryptoQuote(crypto.id.toString())
    }

    if (cryptoQuote.value !== null) {
        MetaData(crypto = crypto, cryptoQuote = cryptoQuote.value!!)
        GeneralData(crypto = crypto, cryptoQuote = cryptoQuote.value!!)
    }
}