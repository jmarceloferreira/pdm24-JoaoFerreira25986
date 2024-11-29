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
        fetchTopStories("home") // Pode ser qualquer categoria, "home" é apenas um exemplo
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

    // Função para buscar o artigo por ID ou título
    fun getArticleById(articleId: String?): Article? {
        return articles.value?.find { it.title == articleId }
    }
}
