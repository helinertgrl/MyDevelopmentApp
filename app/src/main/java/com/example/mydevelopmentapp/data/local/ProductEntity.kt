package com.example.mydevelopmentapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.ktor.http.Url

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String
)