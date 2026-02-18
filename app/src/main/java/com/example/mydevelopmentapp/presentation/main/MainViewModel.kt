package com.example.mydevelopmentapp.presentation.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    var loginMode = mutableStateOf(false)
    var username = mutableStateOf("")
    var password = mutableStateOf("")

    val currentUser = "Helin"

    fun onLoginClick(onSuccess: () -> Unit) {

        if (username.value.isNotBlank() && password.value.isNotBlank()) {
            onSuccess()
        }
    }
}