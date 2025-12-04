package com.example.mydevelopmentapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ShopandProfile(navController: NavHostController){
    Row(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically) {

        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .background(Color.Blue)
                .clickable(onClick = {navController.navigate(Screen.ShopScreen.route)}),
            contentAlignment = Alignment.Center) {
            Text(text = "SHOP",
                fontSize = 25.sp,
                color = Color.White)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .background(Color.Blue)
                .clickable(onClick = {navController.navigate(Screen.ProfileScreen.route)}),
            contentAlignment = Alignment.Center) {
            Text(text = "PROFILE",
                fontSize = 25.sp,
                color = Color.White)
        }
    }
}