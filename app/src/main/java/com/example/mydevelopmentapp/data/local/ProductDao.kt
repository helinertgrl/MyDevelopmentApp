package com.example.mydevelopmentapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydevelopmentapp.Product

@Dao
interface ProductDao{

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(product: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun deleteAll()
}