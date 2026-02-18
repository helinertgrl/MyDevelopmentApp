package com.example.mydevelopmentapp.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mydevelopmentapp.navigation.Screen

@Composable
fun MainScreen(navController: NavHostController){

    var loginMode = remember { mutableStateOf<Boolean>(false)}

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.LightGray)
    ){

        AnimatedVisibility(visible = loginMode.value) {

            BackHandler(enabled = loginMode.value) {
                loginMode.value = false
            }
            UsernamePassword(navController)
        }
        AnimatedVisibility(visible = !loginMode.value) {

            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                mainScreenText("Login"){
                    loginMode.value = true
                }
                mainScreenText("Sign Up"){
                    loginMode.value = true
                }
                mainScreenText("Guest"){}
            }
        }

    }
}

@Composable
fun mainScreenText(text: String, onClick:() -> Unit){
    Box (modifier = Modifier.fillMaxWidth()
        .padding(start = 25.dp, end = 25.dp, bottom = 40.dp)
        .background(Color.Black)
        .clickable(onClick = {onClick() })) {
        Text(text = text,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun UsernamePassword(navController: NavHostController){

    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = username.value,
            onValueChange = {username.value = it},
            label = {Text("Username")}
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextField(
            value = password.value,
            onValueChange = {password.value = it},
            label = {Text("Password")}
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Button(onClick = {navController.navigate(Screen.ShopandProfile.route)}) {
            Text(text = "->",
                fontSize = 40.sp)
        }
    }
}

