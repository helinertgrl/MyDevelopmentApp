package com.example.mydevelopmentapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartEntity>> // Flow kullanarak veritabanı değiştikçe UI otomatik güncellenir

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: CartEntity)

    @Delete
    suspend fun removeFromCart(item: CartEntity)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}