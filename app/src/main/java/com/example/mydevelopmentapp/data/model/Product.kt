package com.example.mydevelopmentapp.data.model

data class Product(
    val name: String,
    val price: Double,
    val imageResourceId: String
) {
    companion object {
        const val MIN_PRICE = 5.00
        const val MAX_PRICE = 20.00

        fun isValidPrice(price: Double): Boolean {
            return price in MIN_PRICE..MAX_PRICE
        }
    }
}