package com.example.mydevelopmentapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String
)