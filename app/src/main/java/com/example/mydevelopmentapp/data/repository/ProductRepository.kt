package com.example.mydevelopmentapp.data.repository

import com.example.mydevelopmentapp.Product
import com.example.mydevelopmentapp.data.api.CoffeeApiService
import com.example.mydevelopmentapp.data.local.ProductDao
import com.example.mydevelopmentapp.data.local.ProductEntity
import com.example.mydevelopmentapp.data.model.ProductResponse

class ProductRepository(
    private val apiService: CoffeeApiService,
    private val productDao: ProductDao
){

    // API'dan ve Room'dan veri çekme
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
}

// Extension fonksiyonları
private fun ProductEntity.toProduct(): Product {
    return Product(
        name = this.name,
        price = this.price,
        imageResourceId = this.imageUrl  // URL artık
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