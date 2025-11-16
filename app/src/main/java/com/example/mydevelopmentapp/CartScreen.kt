package com.example.mydevelopmentapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun CartScreen(navController: NavHostController){

    val total = CartManager.totalPrice

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text ="Toplam Tutar: ${total.toCurrencyString()}")

        LazyColumn {
            items(CartManager.shopCartItems) { product ->
                Text(text = product.name )
            }
        }
    }
}