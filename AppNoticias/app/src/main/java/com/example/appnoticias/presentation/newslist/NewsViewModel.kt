package com.example.appnoticias.presentation.newslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnoticias.data.dto.Article
import com.example.appnoticias.domain.usecase.GetTopStoriesUseCase
import kotlinx.coroutines.launch

class NewsViewModel(private val getTopStoriesUseCase: GetTopStoriesUseCase) : ViewModel() {

    val articles = MutableLiveData<List<Article>>()
    val error = MutableLiveData<String>()

    init {
        // Buscar as notícias ao inicializar o ViewModel
        fetchTopStories("home") // Ou qualquer categoria padrão que você deseja exibir
    }

    fun fetchTopStories(category: String) {
        viewModelScope.launch {
            try {
                val result = getTopStoriesUseCase(category)
                articles.postValue(result)
            } catch (e: Exception) {
                error.postValue(e.message)
            }
        }
    }
}
