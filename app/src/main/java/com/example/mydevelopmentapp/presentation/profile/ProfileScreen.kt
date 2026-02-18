package com.example.mydevelopmentapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mydevelopmentapp.R

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel){

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(
            painter = painterResource(R.drawable.latt),
            contentDescription = "profile image",
            modifier = Modifier.size(200.dp))

        Text(
            text = "Merhaba ${viewModel.userName.value}",
            fontSize = 30.sp
        )

        Text(
            text = viewModel.userEmail.value,
            fontSize = 16.sp
        )
    }
}

