package com.example.cryptotracker.ui.util

import coil.compose.AsyncImage
import com.example.cryptotracker.ui.classes.Crypto
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class CryptoClient {

    private var client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private val retrieveCryptoUrl: String = "https://retrieve-crypto.kouhai.workers.dev/all"

    suspend fun retrieveAllCrypto(): List<Crypto> {
        val gson = Gson()
        val response = this.client.get(retrieveCryptoUrl).bodyAsText()

        return gson.fromJson(response, Array<Crypto>::class.java).toList()
    }
}

val cryptoClient: CryptoClient = CryptoClient()