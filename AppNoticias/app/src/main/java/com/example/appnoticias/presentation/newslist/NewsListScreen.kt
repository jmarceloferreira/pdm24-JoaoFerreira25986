package com.example.appnoticias.presentation.newslist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.appnoticias.data.dto.Article

@Composable
fun NewsListScreen(viewModel: NewsViewModel, navController: NavController) {
    val articles by viewModel.articles.observeAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            NewsItem(
                title = article.title,
                description = article.abstract ?: "Sem descrição disponível",
                imageUrl = article.multimedia?.firstOrNull()?.url, // Passando a URL da imagem
                onClick = {
                    navController.navigate("newsDetail/${article.title}")
                }
            )
        }
    }
}
