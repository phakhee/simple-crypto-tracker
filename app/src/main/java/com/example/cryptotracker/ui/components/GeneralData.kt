package com.example.cryptotracker.ui.components

import androidx.compose.runtime.Composable
import com.example.cryptotracker.ui.classes.Crypto
import com.example.cryptotracker.ui.classes.CryptoQuote
import com.example.cryptotracker.ui.composables.QuoteData
import com.example.cryptotracker.ui.composables.QuoteDataName
import com.example.cryptotracker.ui.composables.QuoteDataValue
import com.example.cryptotracker.ui.composables.QuoteRow
import com.example.cryptotracker.ui.util.formatAmericanDouble
import com.example.cryptotracker.ui.util.round
import kotlin.math.roundToInt

@Composable
fun GeneralData(
    crypto: Crypto,
    cryptoQuote: CryptoQuote
) {
    fun calculateVolumeMarketCap(volume: Double, marketCap: Double): Double {
        return volume / marketCap * 100
    }

    QuoteRow {
        QuoteData(fill=0.3f) {
            QuoteDataName(text = "Rank")
            QuoteDataValue(text = "#${cryptoQuote.cmcRank}")
        }
        QuoteData(fill=0.7f) {
            QuoteDataName(text = "Market Cap")
            QuoteDataValue(
                text = "$${formatAmericanDouble(cryptoQuote.marketCap.round())}"
            )
        }
    }
    QuoteRow {
        QuoteData(fill=0.5f) {
            QuoteDataName(text = "Volume (24h)")
            QuoteDataValue(
                text = "$${formatAmericanDouble(cryptoQuote.volume24h.round())}"
            )
        }
        QuoteData(fill=0.5f) {
            QuoteDataName(text = "Volume/Market cap (24h)")
            QuoteDataValue(
                text = "${calculateVolumeMarketCap(
                    cryptoQuote.volume24h,
                    cryptoQuote.marketCap
                ).round()}%"
            )
        }
    }
    QuoteRow {
        QuoteData(fill=0.53f) {
            QuoteDataName(text = "Circulating supply")
            QuoteDataValue(
                text = "${formatAmericanDouble(cryptoQuote.circulatingSupply.roundToInt())} ${crypto.symbol}"
            )
        }
        QuoteData(fill=0.47f) {
            QuoteDataName(text = "Total supply")
            QuoteDataValue(
                text = "${formatAmericanDouble(cryptoQuote.totalSupply.roundToInt())} ${crypto.symbol}"
            )
        }
    }
    QuoteRow {
        QuoteData {
            QuoteDataName(text = "Fully diluted market cap")
            QuoteDataValue(
                text = "$${formatAmericanDouble(cryptoQuote.fullyDilutedMarketCap.toInt())}"
            )
        }
    }
}