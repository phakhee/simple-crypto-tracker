package com.example.cryptotracker.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.ui.classes.Crypto
import com.example.cryptotracker.ui.classes.CryptoQuote
import com.example.cryptotracker.ui.composables.CryptoDropdownIcon
import com.example.cryptotracker.ui.composables.MetaDataColumn
import com.example.cryptotracker.ui.composables.PriceChange
import com.example.cryptotracker.ui.composables.PriceText
import com.example.cryptotracker.ui.composables.QuoteData
import com.example.cryptotracker.ui.composables.QuoteDataName
import com.example.cryptotracker.ui.composables.QuoteDataValue
import com.example.cryptotracker.ui.composables.QuoteRow
import com.example.cryptotracker.ui.composables.SubHeader
import com.example.cryptotracker.ui.composables.SubText
import com.example.cryptotracker.ui.composables.TimeFilterChip
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
    var selectedTimeSpan by remember { mutableStateOf("24h") }
    val usFormat = NumberFormat.getInstance(Locale.US)

    coroutineScope.launch {
        cryptoQuote.value = cryptoClient.retrieveCryptoQuote(crypto.id.toString())
    }

    fun calculateVolumeMarketCap(volume: Double, marketCap: Double): Double {
        return volume / marketCap * 100
    }

    fun getPriceChange(quote: CryptoQuote): Double {
        return when (selectedTimeSpan) {
            "7d" -> quote.percentChange7d
            "30d" -> quote.percentChange30d
            else -> quote.percentChange24h
        }
    }

    if (cryptoQuote.value !== null) {
        MetaDataColumn {
            QuoteRow {
                CryptoDropdownIcon(url = crypto.logo, description = crypto.id.toString())
                Spacer(modifier = Modifier.width(8.dp))
                SubHeader(text = crypto.name)
                Spacer(modifier = Modifier.width(8.dp))
                SubText(text = crypto.symbol)
            }
            QuoteRow {
                TimeFilterChip(
                    text = "24h",
                    selected = selectedTimeSpan == "24h",
                    onClick = { selectedTimeSpan = "24h" }
                )
                TimeFilterChip(
                    text = "7d",
                    selected = selectedTimeSpan == "7d",
                    onClick = { selectedTimeSpan = "7d" }
                )
                TimeFilterChip(
                    text = "30d",
                    selected = selectedTimeSpan == "30d",
                    onClick = { selectedTimeSpan = "30d" }
                )
            }
            QuoteRow {
                PriceText(text = "$${usFormat.format(cryptoQuote.value!!.price.round())}")
                Spacer(modifier = Modifier.width(8.dp))
                PriceChange(value = getPriceChange(cryptoQuote.value!!).round())
            }
        }
        QuoteRow {
            QuoteData(fill=0.3f) {
                QuoteDataName(text = "Rank")
                QuoteDataValue(text = "#${cryptoQuote.value!!.cmcRank}")
            }
            Spacer(modifier = Modifier.width(8.dp))
            QuoteData(fill=0.7f) {
                QuoteDataName(text = "Market Cap")
                QuoteDataValue(
                    text = "$${usFormat.format(cryptoQuote.value!!.marketCap.round())}"
                )
            }
        }
        QuoteRow {
            QuoteData(fill=0.5f) {
                QuoteDataName(text = "Volume (24h)")
                QuoteDataValue(
                    text = "$${usFormat.format(cryptoQuote.value!!.volume24h.round())}"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            QuoteData(fill=0.5f) {
                QuoteDataName(text = "Volume/Market cap (24h)")
                QuoteDataValue(
                    text = "${calculateVolumeMarketCap(
                        cryptoQuote.value!!.volume24h,
                        cryptoQuote.value!!.marketCap
                    ).round()}%"
                )
            }
        }
        QuoteRow {
            QuoteData(fill=0.53f) {
                QuoteDataName(text = "Circulating supply")
                QuoteDataValue(
                    text = "${usFormat.format(cryptoQuote.value!!.circulatingSupply.roundToInt())} ${crypto.symbol}"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            QuoteData(fill=0.47f) {
                QuoteDataName(text = "Total supply")
                QuoteDataValue(
                    text = "${usFormat.format(cryptoQuote.value!!.totalSupply.roundToInt())} ${crypto.symbol}"
                )
            }
        }
        QuoteRow {
            QuoteData {
                QuoteDataName(text = "Fully diluted market cap")
                QuoteDataValue(
                    text = "$${usFormat.format(cryptoQuote.value!!.fullyDilutedMarketCap.toInt())}"
                )
            }
        }
    }
}