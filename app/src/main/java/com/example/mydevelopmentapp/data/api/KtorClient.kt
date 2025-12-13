package com.example.mydevelopmentapp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object KtorClient {

    val httpClient = HttpClient(Android) {

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true   //API fazla key gönderirse hata vermez
                    isLenient = true
                }
            )
        }
        defaultRequest { url("https://693426b14090fe3bf01f186d.mockapi.io/") }
    }
}
