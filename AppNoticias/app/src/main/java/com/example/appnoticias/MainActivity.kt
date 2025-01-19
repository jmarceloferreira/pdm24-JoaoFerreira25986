package com.example.appnoticias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnoticias.presentation.newslist.NewsListScreen
import com.example.appnoticias.presentation.newslist.NewsViewModel
import com.example.appnoticias.presentation.newslist.NewsViewModelFactory
import com.example.appnoticias.presentation.newsdetail.NewsDetailScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: NewsViewModel = viewModel(factory = NewsViewModelFactory())

            NavHost(navController = navController, startDestination = "news_list") {
                composable("news_list") {
                    NewsListScreen(viewModel = viewModel, navController = navController)
                }
                composable("details/{title}/{description}/{date}/{imageUrl}") { backStackEntry ->
                    val title = URLDecoder.decode(backStackEntry.arguments?.getString("title") ?: "", StandardCharsets.UTF_8.toString())
                    val description = URLDecoder.decode(backStackEntry.arguments?.getString("description") ?: "", StandardCharsets.UTF_8.toString())
                    val date = URLDecoder.decode(backStackEntry.arguments?.getString("date") ?: "", StandardCharsets.UTF_8.toString())
                    val imageUrl = URLDecoder.decode(backStackEntry.arguments?.getString("imageUrl") ?: "", StandardCharsets.UTF_8.toString())
                    NewsDetailScreen(title, description, date, imageUrl)
                }
            }
        }
    }
}
