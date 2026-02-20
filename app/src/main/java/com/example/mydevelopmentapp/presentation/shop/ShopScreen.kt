package com.example.mydevelopmentapp.presentation.shop

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mydevelopmentapp.presentation.shop.components.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(
    viewModel: ShopViewModel
) {
    val products = viewModel.getFilteredProducts()
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    val cartItems by viewModel.cartItems.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Coffee Shop", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = viewModel.searchText.value,
                onValueChange = { viewModel.searchText.value = it },
                label = { Text("Search products") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                FilterChip(
                    selected = viewModel.sortOption.value == "none" && !viewModel.showExpensiveOnly.value,
                    onClick = {
                        viewModel.sortOption.value = "none"
                        viewModel.showExpensiveOnly.value = false
                    },
                    label = { Text("All") }
                )
                FilterChip(
                    selected = viewModel.sortOption.value == "price",
                    onClick = {
                        viewModel.sortOption.value = "price"
                        viewModel.showExpensiveOnly.value = false
                    },
                    label = { Text("Price") }
                )
                FilterChip(
                    selected = viewModel.sortOption.value == "name",
                    onClick = {
                        viewModel.sortOption.value = "name"
                        viewModel.showExpensiveOnly.value = false
                    },
                    label = { Text("Name") }
                )
                FilterChip(
                    selected = viewModel.showExpensiveOnly.value,
                    onClick = {
                        viewModel.showExpensiveOnly.value = !viewModel.showExpensiveOnly.value
                        viewModel.sortOption.value = "none"
                    },
                    label = { Text("Premium") }
                )
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (errorMessage != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = errorMessage ?: "An error occurred", color = MaterialTheme.colorScheme.error)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    items(products) { product ->

                        val isInCart = cartItems.any { it.name == product.name }
                        ProductCard(
                            product = product,
                            isInCart = isInCart,
                            onCartClick = { viewModel.toggleCart(product) }
                        )
                    }
                }
            }
        }
    }
}