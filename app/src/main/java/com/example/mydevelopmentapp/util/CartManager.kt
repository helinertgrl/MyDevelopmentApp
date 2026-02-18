package com.example.mydevelopmentapp.util

import androidx.compose.runtime.mutableStateListOf
import com.example.mydevelopmentapp.data.model.Product

object CartManager {
    val shopCartItems = mutableStateListOf<Product>()

    fun addItems(product: Product) {
        shopCartItems.add(product)
    }

    fun removeProduct(product: Product){
        shopCartItems.remove(product)
    }

    val totalPrice: Double
        get() = shopCartItems.fold(0.0){ acc, product ->
            acc + product.price
        }
}