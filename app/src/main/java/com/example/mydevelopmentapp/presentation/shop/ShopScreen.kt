package com.example.mydevelopmentapp.presentation.shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mydevelopmentapp.R
import com.example.mydevelopmentapp.data.api.CoffeeApiService
import com.example.mydevelopmentapp.data.api.KtorClient
import com.example.mydevelopmentapp.data.local.AppDatabase
import com.example.mydevelopmentapp.data.repository.ProductRepository
import com.example.mydevelopmentapp.navigation.Screen
import com.example.mydevelopmentapp.presentation.shop.components.ShopCard
import com.example.mydevelopmentapp.util.CartManager
import com.example.mydevelopmentapp.util.sortByField
import com.example.mydevelopmentapp.util.toCurrencyString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(navController: NavHostController) {
    val context = LocalContext.current

    /*val products = listOf(
        Product("Latte", 12.99, R.drawable.latt),
        Product("Espresso", 9.99, R.drawable.latt),
        Product("Americano", 11.49, R.drawable.latt),
        Product("Cappuccino", 10.49, R.drawable.latt),
        Product("Macchiato", 8.49, R.drawable.latt),
        Product("Affogato", 7.49, R.drawable.latt),
        Product("Iced Coffee", 6.49, R.drawable.latt),
        Product("Turkish Coffee", 11.49, R.drawable.latt),
        Product("Cold Brew", 12.49, R.drawable.latt),
        Product("Flat White", 6.49, R.drawable.latt),
        Product("Mocha", 6.49, R.drawable.latt),
        Product("White Chocolate Mocha", 5.49, R.drawable.latt)
    ).filter { Product.isValidPrice(it.price) }

     */

    // State'ler
    var products by remember { mutableStateOf<List<Product>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    val repository = remember {
        ProductRepository(
            CoffeeApiService(KtorClient.httpClient),
            AppDatabase.getDatabase(context).productDao()
        )
    }


    LaunchedEffect(Unit) {
        isLoading = true
        try {
            products = repository.getProducts()
            errorMessage = null
        } catch (e: Exception) {
            errorMessage = "Hata: ${e.message}"
            products = emptyList()
        } finally {
            isLoading = false
        }
    }



    val searchText = remember { mutableStateOf("") }           //textfield içinde arama durumu
    val showExpensiveOnly = remember { mutableStateOf<Boolean>(false) }   //11den pahalı state durumu
    val sortOption = remember { mutableStateOf<String>("none") }    //Kullanıcının hangi sıralamayı seçtiğini tutuyor.


    val searchedProducts = products.filter { product ->
        searchText.value.isBlank() || product.name.contains(searchText.value, ignoreCase = true)
    }


    val filteredProducts = if (showExpensiveOnly.value){             //11denn pahalı olanları filtreleme
        searchedProducts.filter { product ->
            product.price > 11.00
        }
    } else {
        searchedProducts
    }


    val sortedProducts = when(sortOption.value) {
        "price" -> filteredProducts.sortByField { it.price }
        "name" -> filteredProducts.sortByField { it.name }
        else -> filteredProducts
    }

    Scaffold (
        topBar = {TopAppBar(
            title = {Text(text = "Shopping")},
            actions = {
                IconButton(
                    onClick = {navController.navigate(Screen.CartScreen.route)}
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "sepet")
                }
            })})
    {
        innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Loading göster
            if (isLoading) {
                Text(
                    text = "Yükleniyor...",
                    modifier = Modifier.padding(16.dp)
                )
            }


            OutlinedTextField(               //arama çubuğu
                value = searchText.value,
                onValueChange = {newText ->
                    searchText.value = newText
                },
                label = {Text("Search for products")}
            )


            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                FilterChip(
                    selected = sortOption.value == "none" && !showExpensiveOnly.value,
                    onClick = {
                        sortOption.value = "none"
                        showExpensiveOnly.value = false
                    },
                    label = {Text("All")}
                )

                FilterChip(
                    selected = sortOption.value == "price",
                    onClick = {
                        sortOption.value = "price"
                        showExpensiveOnly.value = false
                    },
                    label = {Text("Price")}
                )

                FilterChip(
                    selected = sortOption.value == "name",
                    onClick = {
                        sortOption.value = "name"
                        showExpensiveOnly.value = false
                    },
                    label = {Text("Name")}
                )

                FilterChip(
                    selected = showExpensiveOnly.value,
                    onClick = {
                        showExpensiveOnly.value = !showExpensiveOnly.value
                        sortOption.value = "none"
                    },
                    label = {Text("Expensive")}
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(sortedProducts) { product ->
                    ShopCard(product)
                }
            }
        }
    }
}


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