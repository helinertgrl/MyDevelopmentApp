package com.example.mydevelopmentapp.presentation.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    // İleride bu veriler Firebase'den gelecek
    val userName = mutableStateOf("Helin")
    val userEmail = mutableStateOf("helin@example.com")
}