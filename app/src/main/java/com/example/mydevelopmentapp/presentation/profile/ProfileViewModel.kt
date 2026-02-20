package com.example.mydevelopmentapp.presentation.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    val userEmail = mutableStateOf(auth.currentUser?.email ?: "Unknown User")
    val userName = mutableStateOf(auth.currentUser?.email?.substringBefore("@")?.replaceFirstChar { it.uppercase() } ?: "Guest")

    fun logOut(onLogOutSuccess: () -> Unit) {
        auth.signOut()
        onLogOutSuccess()
    }
}