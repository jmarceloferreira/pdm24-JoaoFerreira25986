package com.example.vinylstore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinylstore.ui.screen.CartScreen
import com.example.vinylstore.ui.screen.HomeScreen
import com.example.vinylstore.ui.screens.LoginScreen
import com.example.vinylstore.ui.screens.RegisterScreen
import com.google.firebase.auth.FirebaseAuth
import com.example.vinylstore.data.CartRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.example.vinylstore.ui.screen.ForgotPasswordScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val cartRepository = CartRepository(FirebaseFirestore.getInstance())

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToForgotPasswordScreen = { navController.navigate("forgot_password") } // Adicionado aqui
            )
        }
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.popBackStack() }
            )
        }
        composable("home") {
            HomeScreen(
                onCartClick = {
                    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""
                    navController.navigate("cart/$userEmail")
                }
            )
        }
        composable("cart/{userEmail}") { backStackEntry ->
            val userEmail = backStackEntry.arguments?.getString("userEmail") ?: ""
            CartScreen(cartRepository = cartRepository, userEmail = userEmail)
        }
        composable("forgot_password") {
            ForgotPasswordScreen(
                onPasswordReset = { navController.popBackStack() }
            )
        }
    }
}
