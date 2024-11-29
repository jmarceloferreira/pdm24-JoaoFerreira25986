package com.example.appnoticias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnoticias.presentation.newslist.NewsListScreen
import com.example.appnoticias.presentation.newsdetail.NewsDetailScreen
import com.example.appnoticias.presentation.newslist.NewsViewModel
import com.example.appnoticias.presentation.newslist.NewsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()  // Define o controlador de navegação
            val viewModel: NewsViewModel = viewModel(factory = NewsViewModelFactory())

            // Definição da navegação usando NavHost
            NavHost(navController = navController, startDestination = "newsList") {
                composable("newsList") {
                    // Passando o navController para a tela de lista de notícias
                    NewsListScreen(viewModel = viewModel, navController = navController)
                }
                composable("newsDetail/{articleId}") { backStackEntry ->
                    val articleId = backStackEntry.arguments?.getString("articleId")
                    val article = viewModel.getArticleById(articleId)  // Agora funciona dentro do contexto composable
                    article?.let {
                        // Passando o artigo para a tela de detalhes
                        NewsDetailScreen(article = it)
                    }
                }
            }
        }
    }
}
