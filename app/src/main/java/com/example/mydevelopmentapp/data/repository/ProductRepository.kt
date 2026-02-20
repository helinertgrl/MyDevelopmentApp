package com.example.mydevelopmentapp.data.repository

import com.example.mydevelopmentapp.data.api.CoffeeApiService
import com.example.mydevelopmentapp.data.local.CartDao
import com.example.mydevelopmentapp.data.local.CartEntity
import com.example.mydevelopmentapp.data.local.ProductDao
import com.example.mydevelopmentapp.data.local.ProductEntity
import com.example.mydevelopmentapp.data.model.Product
import com.example.mydevelopmentapp.data.model.ProductResponse
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val apiService: CoffeeApiService,
    private val productDao: ProductDao,
    private val cartDao: CartDao
){

    suspend fun getProducts(): List<Product> {
        val cachedProducts = productDao.getAllProducts()

        if (cachedProducts.isNotEmpty()){
            return cachedProducts.map {it.toProduct()}
        }

        val apiResponse = apiService.getProducts()

        val entities = apiResponse.map { it.toEntity()}
        productDao.insertAll(entities)

        return entities.map { it.toProduct() }
    }

    val allCartItems: Flow<List<CartEntity>> = cartDao.getAllCartItems()

    suspend fun addToCart(product: Product) {
        cartDao.addToCart(CartEntity(
            id = product.name.hashCode(),
            name = product.name,
            price = product.price,
            imageUrl = product.imageResourceId
        ))
    }

    suspend fun removeFromCart(product: Product) {
        cartDao.removeFromCart(CartEntity(
            id = product.name.hashCode(),
            name = product.name,
            price = product.price,
            imageUrl = product.imageResourceId
        ))
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}

private fun ProductEntity.toProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        imageResourceId = this.imageUrl
    )
}

private fun ProductResponse.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        imageUrl = this.imageUrl
    )
}