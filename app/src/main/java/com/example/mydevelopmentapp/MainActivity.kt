package com.example.mydevelopmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mydevelopmentapp.navigation.AppNavigation
import com.example.mydevelopmentapp.ui.theme.MyDevelopmentAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyDevelopmentAppTheme {
                AppNavigation()
            }
        }
    }
}


