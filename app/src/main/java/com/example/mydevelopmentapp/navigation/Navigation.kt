package com.example.mydevelopmentapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val database = AppDatabase.getDatabase(context)
    val repository = ProductRepository(
        apiService = CoffeeApiService(KtorClient.httpClient),
        productDao = database.productDao(),
        cartDao = database.cartDao()
    )

    val bottomNavItems = listOf(
        BottomNavItem.Shop,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isMainScreen = currentDestination?.hasRoute<Screen.Main>() == true

    Scaffold(
        bottomBar = {
            if (!isMainScreen) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true

                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = isSelected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Main,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Screen.Main> {
                val mainViewModel: MainViewModel = viewModel()
                MainScreen(navController, mainViewModel)
            }

            composable<Screen.Shop> {
                val shopViewModel: ShopViewModel = viewModel(factory = ViewModelFactory(repository))
                ShopScreen(viewModel = shopViewModel)
            }

            composable<Screen.Profile> {
                val profileViewModel: ProfileViewModel = viewModel()
                ProfileScreen(navController, profileViewModel)
            }

            composable<Screen.Cart> {
                val cartViewModel: CartViewModel = viewModel(factory = ViewModelFactory(repository))
                CartScreen(viewModel =  cartViewModel)
            }
        }
    }
}