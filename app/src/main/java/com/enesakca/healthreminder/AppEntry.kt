package com.enesakca.healthreminder

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun AppEntry(navController: NavHostController, context: Context) {
    val isRemembered = isRememberMe(context)

    LaunchedEffect(Unit) {
        if (isRemembered) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    NavHost(navController, startDestination = if (isRemembered) "home" else "login") {
        composable("login") { LoginScreen(onLoginSuccess = {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }, context = context) }
        composable("home") { HomeScreen() }
        // diğer ekranlar...
    }
}
