package com.example.mydevelopmentapp.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mydevelopmentapp.navigation.Screen

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val isLoginMode = viewModel.isLoginMode.value
    val isLoading = viewModel.isLoading.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Başlık
        Text(
            text = if (isLoginMode) "Giriş Yap" else "Kayıt Ol",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // E-posta Alanı
        OutlinedTextField(
            value = viewModel.email.value,
            onValueChange = { viewModel.email.value = it },
            label = { Text("E-posta") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Şifre Alanı
        OutlinedTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            label = { Text("Şifre") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Hata Mesajı Gösterimi
        if (viewModel.errorMessage.value != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = viewModel.errorMessage.value!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Giriş / Kayıt Butonu
        Button(
            onClick = {
                viewModel.onAuthClick {
                    // Başarılı olursa Shop ekranına geç ve geri dönmeyi engelle
                    navController.navigate(Screen.Shop) {
                        popUpTo(Screen.Main) { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = !isLoading // Yükleniyorken butona tekrar basılmasın
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(text = if (isLoginMode) "Giriş" else "Kayıt Ol", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mod Değiştirme Metni
        Text(
            text = if (isLoginMode) "Hesabın yok mu? Kayıt Ol" else "Zaten hesabın var mı? Giriş Yap",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                viewModel.isLoginMode.value = !viewModel.isLoginMode.value
                viewModel.errorMessage.value = null // Mod değişirken hatayı temizle
            }
        )
    }
}