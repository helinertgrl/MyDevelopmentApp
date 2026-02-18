package com.example.mydevelopmentapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mydevelopmentapp.data.api.CoffeeApiService
import com.example.mydevelopmentapp.data.api.KtorClient
import com.example.mydevelopmentapp.data.local.AppDatabase
import com.example.mydevelopmentapp.data.repository.ProductRepository
import com.example.mydevelopmentapp.presentation.cart.CartScreen
import com.example.mydevelopmentapp.presentation.cart.CartViewModel
import com.example.mydevelopmentapp.presentation.main.MainScreen
import com.example.mydevelopmentapp.presentation.main.MainViewModel
import com.example.mydevelopmentapp.presentation.profile.ProfileScreen
import com.example.mydevelopmentapp.presentation.profile.ProfileViewModel
import com.example.mydevelopmentapp.presentation.shop.ShopScreen
import com.example.mydevelopmentapp.presentation.shop.ShopViewModel
import com.example.mydevelopmentapp.util.ViewModelFactory

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val context = LocalContext.current

    val database = AppDatabase.getDatabase(context)
    val repository = ProductRepository(
        apiService = CoffeeApiService(KtorClient.httpClient),
        productDao = database.productDao(),
        cartDao = database.cartDao()
    )

    NavHost(
        navController = navController,
        startDestination = Screen.Main
    ) {
        composable<Screen.Main> {
            val mainViewModel: MainViewModel = viewModel()
            MainScreen(navController,mainViewModel)
        }

        composable<Screen.Shop> {
            val shopViewModel: ShopViewModel = viewModel(factory = ViewModelFactory(repository))
            ShopScreen(navController,shopViewModel)
        }

        composable<Screen.Profile> {
            val profileViewModel: ProfileViewModel = viewModel()
            ProfileScreen(navController,profileViewModel)
        }

        composable<Screen.Cart> {
            val cartViewModel: CartViewModel = viewModel(factory = ViewModelFactory(repository))
            CartScreen(navController)
        }
    }
}
