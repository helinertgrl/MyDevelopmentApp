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
import androidx.navigation.serialization.RouteEncoder

@OptIn(ExperimentalMaterial3Api::class)
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
                    shopCard(product)
                }
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