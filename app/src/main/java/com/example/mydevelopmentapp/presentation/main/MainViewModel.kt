package com.example.mydevelopmentapp.presentation.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel : ViewModel() {

    // Firebase Auth nesnesi
    private val auth = FirebaseAuth.getInstance()

    // Ekranın hangi modda olduğunu tutar (Giriş mi, Kayıt mı?)
    var isLoginMode = mutableStateOf(true)

    var email = mutableStateOf("")
    var password = mutableStateOf("")

    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    fun onAuthClick(onSuccess: () -> Unit) {
        // Boş alan kontrolü
        if (email.value.isBlank() || password.value.isBlank()) {
            errorMessage.value = "Lütfen e-posta ve şifre girin."
            return
        }

        isLoading.value = true
        errorMessage.value = null

        if (isLoginMode.value) {
            // Firebase ile Giriş Yap
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    isLoading.value = false
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        errorMessage.value = task.exception?.localizedMessage ?: "Giriş başarısız oldu."
                    }
                }
        } else {
            // Firebase ile Yeni Kayıt Oluştur
            auth.createUserWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    isLoading.value = false
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        errorMessage.value = task.exception?.localizedMessage ?: "Kayıt başarısız oldu."
                    }
                }
        }
    }
}