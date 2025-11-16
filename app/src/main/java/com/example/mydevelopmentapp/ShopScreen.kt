package com.example.mydevelopmentapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController



@Composable
fun ShopScreen(navController: NavHostController) {


    val products = listOf(
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


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Button(
            onClick = { showExpensiveOnly.value = !showExpensiveOnly.value }
        ) {
            Text(
                text = if (showExpensiveOnly.value){
                    "Tüm Ürünleri Göster"
                }else{
                    "Sadece Pahalı Ürünleri Göster (> $11.00)"
                }
            )
        }

        OutlinedTextField(                                 //arama çubuğu
            value = searchText.value,
            onValueChange = {newText ->
                searchText.value = newText
            },
            label = {Text("Ürün Ara")}
        )


        Button(onClick = {navController.navigate("CartScreen")}) {
            Text(text = "Sepeti Gör")
        }

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Button(onClick = {sortOption.value = "none"}) {
                Text("Sıralama Yok")
            }
            Button(onClick = {sortOption.value = "price"}) {
                Text("Fiyata Göre")
            }
            Button(onClick = {sortOption.value = "name"}) {
                Text("İsme Göre")
            }
        }



        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(sortedProducts) { product ->
                shopCard(product)
            }
        }
    }
}


@Composable
fun shopCard(
     product: Product
){
    Card(
        modifier = Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),

        ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(product.imageResourceId), contentDescription = "coffee")
            Text(text = product.name,
                fontSize = 30.sp,
                color = Color.White)
            Text(text = product.price.toCurrencyString(),
                fontSize = 18.sp,
                color = Color.White)
            Button(onClick = { CartManager.addItems(product)}) {
                Text(text = "+",
                    fontSize = 20.sp)
            }
        }
    }
}

data class Product(
    val name: String,
    val price: Double,
    val imageResourceId: Int
) {
    companion object {
        const val MIN_PRICE = 5.00
        const val MAX_PRICE = 20.00

        fun isValidPrice(price: Double): Boolean {
            return price in MIN_PRICE..MAX_PRICE
        }
    }
}