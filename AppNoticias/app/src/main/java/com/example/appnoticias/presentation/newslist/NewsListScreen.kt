package com.example.appnoticias.presentation.newslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun NewsListScreen(viewModel: NewsViewModel, navController: NavController) {
    val articles by viewModel.articles.observeAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            NewsItem(article = article, navController = navController)
        }
    }
}
