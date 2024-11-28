package com.example.appnoticias.presentation.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appnoticias.data.repository.NYTRepositoryImpl
import com.example.appnoticias.domain.usecase.GetTopStoriesUseCase
import com.example.appnoticias.data.api.RetrofitInstance

class NewsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            val repository = NYTRepositoryImpl(RetrofitInstance.api)
            val getTopStoriesUseCase = GetTopStoriesUseCase(repository)
            return NewsViewModel(getTopStoriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
