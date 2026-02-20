package com.example.mydevelopmentapp.presentation.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var isLoginMode = mutableStateOf(true)

    var email = mutableStateOf("")
    var password = mutableStateOf("")

    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    fun onAuthClick(onSuccess: () -> Unit) {

        if (email.value.isBlank() || password.value.isBlank()) {
            errorMessage.value = "Please enter email and password."
            return
        }

        isLoading.value = true
        errorMessage.value = null

        if (isLoginMode.value) {
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    isLoading.value = false
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        errorMessage.value = task.exception?.localizedMessage ?: "Login failed."
                    }
                }
        } else {
            auth.createUserWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    isLoading.value = false
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        errorMessage.value = task.exception?.localizedMessage ?: "Registration failed."
                    }
                }
        }
    }
}