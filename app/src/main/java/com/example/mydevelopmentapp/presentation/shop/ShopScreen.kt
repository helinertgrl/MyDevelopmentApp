package com.example.mydevelopmentapp.presentation.shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mydevelopmentapp.navigation.Screen
import com.example.mydevelopmentapp.presentation.shop.components.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(
    navController: NavHostController,
    viewModel: ShopViewModel) {

    val products = viewModel.getFilteredProducts()
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    Scaffold (
        topBar = {TopAppBar(
            title = {Text(text = "Shopping")},
            actions = {
                IconButton(
                    onClick = {navController.navigate(Screen.Cart)}
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
            OutlinedTextField(               //arama çubuğu
                value = viewModel.searchText.value,
                onValueChange = {viewModel.searchText.value = it},
                label = {Text("Search products")}
            )


            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                FilterChip(
                    selected = viewModel.sortOption.value == "none" && !viewModel.showExpensiveOnly.value,
                    onClick = {
                        viewModel.sortOption.value = "none"
                        viewModel.showExpensiveOnly.value = false
                    },
                    label = {Text("All")}
                )

                FilterChip(
                    selected = viewModel.sortOption.value == "price",
                    onClick = {
                        viewModel.sortOption.value = "price"
                        viewModel.showExpensiveOnly.value = false
                    },
                    label = {Text("Price")}
                )

                FilterChip(
                    selected = viewModel.sortOption.value == "name",
                    onClick = {
                        viewModel.sortOption.value = "name"
                        viewModel.showExpensiveOnly.value = false
                    },
                    label = {Text("Name")}
                )

                FilterChip(
                    selected = viewModel.showExpensiveOnly.value,
                    onClick = {
                        viewModel.showExpensiveOnly.value = !viewModel.showExpensiveOnly.value
                        viewModel.sortOption.value = "none"
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
                items(products) { product ->
                    ProductCard(product = product)
                }
            }
        }
    }
}