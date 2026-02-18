package com.example.mydevelopmentapp.data.api

import com.example.mydevelopmentapp.data.model.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CoffeeApiService( private val client: HttpClient){
    suspend fun getProducts(): List<ProductResponse> {
        return client.get("products/products").body()
    }
}