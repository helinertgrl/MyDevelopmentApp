package com.example.mydevelopmentapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mydevelopmentapp.data.model.Product

@Entity(tableName = "cart_items")
data class CartEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int = 1
)

fun CartEntity.toProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        imageResourceId = this.imageUrl
    )
}