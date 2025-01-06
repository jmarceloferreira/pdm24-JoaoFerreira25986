package com.example.vinylstore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinylstore.ui.screen.CartScreen
import com.example.vinylstore.ui.screen.HomeScreen
import com.example.vinylstore.ui.screens.LoginScreen
import com.example.vinylstore.ui.screens.RegisterScreen
import com.example.vinylstore.ui.screen.ForgotPasswordScreen
import com.example.vinylstore.ui.screen.ProfileScreen
import com.example.vinylstore.ui.screen.PaymentScreen
import com.example.vinylstore.data.CartRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.vinylstore.ui.screen.PurchaseHistoryScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val cartRepository = CartRepository(FirebaseFirestore.getInstance())
    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToForgotPasswordScreen = { navController.navigate("forgot_password") }
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
                    navController.navigate("cart/$userEmail")
                },
                onProfileClick = { navController.navigate("profile") }
            )
        }
        composable("cart/{userEmail}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("userEmail") ?: ""
            CartScreen(
                cartRepository = cartRepository,
                userEmail = email,
                onPaymentClick = { navController.navigate("payment") }
            )
        }
        composable("forgot_password") {
            ForgotPasswordScreen(
                onPasswordReset = { navController.popBackStack() }
            )
        }
        composable("profile") {
            ProfileScreen(
                onViewHistoryClick = { navController.navigate("purchase_history") }
            )
        }
        composable("payment") {
            PaymentScreen(
                cartRepository = cartRepository,
                userEmail = userEmail,
                onPaymentSuccess = { navController.navigate("home") }
            )
        }
        composable("purchase_history") {
            PurchaseHistoryScreen(
                userEmail = userEmail
            )
        }
    }
}
