package com.example.mydevelopmentapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "MainScreen") {
        composable ("MainScreen"){
            MainScreen(navController)
        }

        composable("ShopandProfile") {
            ShopandProfile(navController)
        }

        composable("ShopScreen") {
            ShopScreen(navController)
        }

        composable("ProfileScreen") {
            ProfileScreen(navController)
        }

        composable("CartScreen") {
            CartScreen(navController)
        }
    }
}