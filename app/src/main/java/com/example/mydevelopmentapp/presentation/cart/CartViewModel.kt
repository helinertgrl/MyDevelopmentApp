package com.example.mydevelopmentapp.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydevelopmentapp.data.model.Product
import com.example.mydevelopmentapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.mydevelopmentapp.data.local.toProduct

class CartViewModel(private val repository: ProductRepository) : ViewModel() {


    val cartItems = repository.allCartItems.map { entities ->
        entities.map { it.toProduct() } // Entity -> Model dönüşümü
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val totalPrice = cartItems.map { items ->
        items.sumOf { it.price }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )

    fun removeFromCart(product: Product) {
        viewModelScope.launch {
            repository.removeFromCart(product)
        }
    }
}