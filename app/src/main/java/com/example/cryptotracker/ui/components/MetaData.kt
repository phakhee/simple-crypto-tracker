package com.example.cryptotracker.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.cryptotracker.ui.classes.Crypto
import com.example.cryptotracker.ui.classes.CryptoQuote
import com.example.cryptotracker.ui.composables.CryptoDropdownIcon
import com.example.cryptotracker.ui.composables.MetaDataColumn
import com.example.cryptotracker.ui.composables.PriceChange
import com.example.cryptotracker.ui.composables.PriceText
import com.example.cryptotracker.ui.composables.QuoteRow
import com.example.cryptotracker.ui.composables.SubHeader
import com.example.cryptotracker.ui.composables.SubText
import com.example.cryptotracker.ui.composables.TimeFilterChip
import com.example.cryptotracker.ui.theme.NeonGreen
import com.example.cryptotracker.ui.theme.NeonRed
import com.example.cryptotracker.ui.util.formatAmericanDouble
import com.example.cryptotracker.ui.util.round
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun MetaData(
    crypto: Crypto,
    cryptoQuote: CryptoQuote
) {
    var selectedTimeSpan by remember { mutableStateOf("24h") }

    fun handlePriceChange(timeSpan: String) {
        selectedTimeSpan = timeSpan
    }

    fun getPriceChange(): Double {
        return when (selectedTimeSpan) {
            "7d" -> cryptoQuote.percentChange7d
            "30d" -> cryptoQuote.percentChange30d
            else -> cryptoQuote.percentChange24h
        }
    }

    fun calculateFormerPrice(value: Double, percentage: Double): Double {
        val result = value * (percentage / 100.0)
        return (cryptoQuote.price - result).round()
    }

    MetaDataColumn {
        QuoteRow {
            CryptoDropdownIcon(url = crypto.logo, description = crypto.id.toString())
            SubHeader(text = crypto.name)
            SubText(text = crypto.symbol)
        }
        QuoteRow {
            TimeFilterChip(
                text = "24h",
                selected = selectedTimeSpan == "24h",
                onClick = { handlePriceChange("24h") }
            )
            TimeFilterChip(
                text = "7d",
                selected = selectedTimeSpan == "7d",
                onClick = { handlePriceChange("7d") }
            )
            TimeFilterChip(
                text = "30d",
                selected = selectedTimeSpan == "30d",
                onClick = { handlePriceChange("30d") }
            )
        }
        QuoteRow {
            PriceText(text = "$${formatAmericanDouble(cryptoQuote.price.round())}")
            PriceChange(value = getPriceChange().round())
        }
        QuoteRow {
            Chart(
                chart = lineChart(
                    lines = listOf(
                        lineSpec(lineColor = if (getPriceChange() > 0) NeonGreen else NeonRed)
                    )
                ),
                model = entryModelOf(
                    calculateFormerPrice(
                        cryptoQuote.price,
                        getPriceChange()
                    ),
                    cryptoQuote.price.round()
                ),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis()
            )
        }
    }
}