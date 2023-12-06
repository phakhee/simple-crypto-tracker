package com.example.cryptotracker.ui.classes

import com.fasterxml.jackson.annotation.JsonProperty

data class Crypto(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,

    @JsonProperty("is_active")
    val isActive: Long,

    @JsonProperty("first_historical_data")
    val firstHistoricalData: String,

    @JsonProperty("last_historical_data")
    val lastHistoricalData: String,
    val platform: Any?,
)