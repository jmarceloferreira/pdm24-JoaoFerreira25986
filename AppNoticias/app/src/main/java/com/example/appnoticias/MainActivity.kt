package com.example.appnoticias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appnoticias.presentation.newslist.NewsListScreen
import com.example.appnoticias.presentation.newslist.NewsViewModel
import com.example.appnoticias.presentation.newslist.NewsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Instanciar o ViewModel e passar para o NewsListScreen
            val viewModel: NewsViewModel = viewModel(factory = NewsViewModelFactory())
            NewsListScreen(viewModel = viewModel)
        }
    }
}
