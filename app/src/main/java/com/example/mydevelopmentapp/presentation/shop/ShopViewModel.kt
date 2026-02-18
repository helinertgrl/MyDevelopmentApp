package com.example.mydevelopmentapp.presentation.shop

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydevelopmentapp.data.model.Product
import com.example.mydevelopmentapp.data.repository.ProductRepository
import com.example.mydevelopmentapp.util.sortByField
import kotlinx.coroutines.launch

class ShopViewModel (
    private val repository: ProductRepository
): ViewModel() {

    // State'ler
    var products =  mutableStateOf<List<Product>>(emptyList())
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    val searchText = mutableStateOf("")
    val showExpensiveOnly =  mutableStateOf<Boolean>(false)
    val sortOption =  mutableStateOf<String>("none")     //Kullanıcının hangi sıralamayı seçtiğini tutuyor.

    init {
        loadProducts()
    }

    fun loadProducts(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                products.value = repository.getProducts()
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "Hata: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun getFilteredProducts(): List<Product> {
        val searched = products.value.filter {
            it.name.contains(searchText.value, ignoreCase = true) || searchText.value.isBlank()
        }

        val filtered = if (showExpensiveOnly.value) {
            searched.filter { it.price > 11.00 }
        } else {
            searched
        }

        return when (sortOption.value) {
            "price" -> filtered.sortByField { it.price }
            "name" -> filtered.sortByField { it.name }
            else -> filtered
        }
    }
}