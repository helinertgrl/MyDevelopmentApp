package com.example.mydevelopmentapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: Screen, val icon: ImageVector, val label: String) {
    data object Shop : BottomNavItem(Screen.Shop, Icons.Default.Home, "Shop")
    data object Cart : BottomNavItem(Screen.Cart, Icons.Default.ShoppingCart, "Cart")
    data object Profile : BottomNavItem(Screen.Profile, Icons.Default.Person, "Profile")
}