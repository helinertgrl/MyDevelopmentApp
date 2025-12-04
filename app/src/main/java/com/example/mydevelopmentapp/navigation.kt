package com.example.mydevelopmentapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable (Screen.MainScreen.route){
            MainScreen(navController)
        }

        composable(Screen.ShopandProfile.route) {
            ShopandProfile(navController)
        }

        composable(Screen.ShopScreen.route) {
            ShopScreen(navController)
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController)
        }

        composable(Screen.CartScreen.route) {
            CartScreen(navController)
        }
    }
}

sealed class Screen(val route: String){
    object MainScreen : Screen("MainScreen")
    object ShopandProfile: Screen("ShopandProfile")
    object ShopScreen :Screen("ShopScreen")
    object ProfileScreen :Screen("ProfileScreen")
    object CartScreen :Screen("CartScreen")
}