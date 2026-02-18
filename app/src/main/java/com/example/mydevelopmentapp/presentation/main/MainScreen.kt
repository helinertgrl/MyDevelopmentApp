package com.example.mydevelopmentapp.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mydevelopmentapp.navigation.Screen
import com.example.mydevelopmentapp.presentation.main.components.MainMenuButton

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel){

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.LightGray)
    ){
        AnimatedVisibility(visible = viewModel.loginMode.value) {
            BackHandler(enabled = viewModel.loginMode.value) {
                viewModel.loginMode.value = false
            }
            LoginForm(navController,viewModel)
        }

        AnimatedVisibility(visible = !viewModel.loginMode.value) {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainMenuButton("Login") { viewModel.loginMode.value = true }
                MainMenuButton("Sign Up") { viewModel.loginMode.value = true }
                MainMenuButton("Guest") {  }
            }
        }

    }
}

@Composable
fun LoginForm(navController: NavHostController, viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.username.value,
            onValueChange = { viewModel.username.value = it },
        label = { Text("Username") }
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Button(onClick = {
            viewModel.onLoginClick {
                navController.navigate(Screen.Shop) {
                    popUpTo(Screen.Main) { inclusive = true }
                }
            }
        }) {
            Text(text = "->", fontSize = 40.sp)
        }
    }
}
