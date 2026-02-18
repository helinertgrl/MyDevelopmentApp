package com.example.mydevelopmentapp.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mydevelopmentapp.presentation.cart.components.CartItem
import com.example.mydevelopmentapp.util.CartManager
import com.example.mydevelopmentapp.util.toCurrencyString
import androidx.compose.foundation.lazy.items


@Composable
fun CartScreen(navController: NavHostController){

    val total = CartManager.totalPrice

    Column(
        modifier = Modifier.fillMaxSize())
    {
        Text(text ="Toplam Tutar: ${total.toCurrencyString()}",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(CartManager.shopCartItems) { product ->
                CartItem(product = product)
            }
        }
    }
}