package com.example.mydevelopmentapp.presentation.shop.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mydevelopmentapp.R
import com.example.mydevelopmentapp.data.model.Product
import com.example.mydevelopmentapp.util.CartManager
import com.example.mydevelopmentapp.util.toCurrencyString

@Composable
fun ProductCard(
    product: Product
){
    Card(
        modifier = Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),

        ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                model = product.imageResourceId,  // URL buraya
                contentDescription = "coffee",
                modifier = Modifier.fillMaxWidth()
                    .height(150.dp),
                placeholder = painterResource(R.drawable.latt),  // Yüklenirken gösterilecek
                error = painterResource(R.drawable.latt),// Hata durumunda gösterilecek
                contentScale = ContentScale.Crop
            )

            Text(text = product.name,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .height(60.dp),
                maxLines = 2,
                minLines = 2,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center
            )

            Text(text = product.price.toCurrencyString(),
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp))


            if (CartManager.shopCartItems.contains(product)){
                Button(onClick = { CartManager.removeProduct(product)},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    Text(text = "-",
                        fontSize = 20.sp)
                }
            }else{
                Button(onClick = { CartManager.addItems(product)}) {
                    Text(text = "+",
                        fontSize = 20.sp)
                }
            }
        }
    }
}