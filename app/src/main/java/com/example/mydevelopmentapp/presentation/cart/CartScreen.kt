package com.example.mydevelopmentapp.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mydevelopmentapp.util.CartManager
import com.example.mydevelopmentapp.util.toCurrencyString

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

                Card {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically)
                    {
                        Text(text = product.name,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp)

                        Text(text = product.price.toCurrencyString(),
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp)

                        Button(onClick = { CartManager.removeProduct(product)},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                            Text("X")
                        }
                    }
                }
            }
        }
    }
}