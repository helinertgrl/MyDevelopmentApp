package com.example.mydevelopmentapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen{
    @Serializable data object Main : Screen
    @Serializable data object Shop : Screen
    @Serializable data object Profile : Screen
    @Serializable data object Cart : Screen
}