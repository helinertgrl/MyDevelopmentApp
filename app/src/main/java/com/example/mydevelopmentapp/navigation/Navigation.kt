package com.example.mydevelopmentapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mydevelopmentapp.presentation.cart.CartScreen
import com.example.mydevelopmentapp.presentation.main.MainScreen
import com.example.mydevelopmentapp.presentation.profile.ProfileScreen
import com.example.mydevelopmentapp.presentation.shop.ShopScreen
import com.example.mydevelopmentapp.presentation.shop.ShopViewModel

@Composable
fun AppNavigation(shopViewModel: ShopViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main) {
        composable<Screen.Main> {
            MainScreen(navController)
        }

        composable<Screen.Shop> {
            ShopScreen(navController,shopViewModel)
        }

        composable<Screen.Profile> {
            ProfileScreen(navController)
        }

        composable<Screen.Cart> {
            CartScreen(navController)
        }
    }
}
