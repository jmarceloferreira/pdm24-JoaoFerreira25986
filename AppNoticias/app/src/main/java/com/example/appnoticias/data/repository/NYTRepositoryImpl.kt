package com.example.appnoticias.data.repository

import com.example.appnoticias.data.api.NYTApi
import com.example.appnoticias.data.dto.Article
import com.example.appnoticias.domain.repository.NYTRepository

class NYTRepositoryImpl(
    private val api: NYTApi
) : NYTRepository {
    override suspend fun getTopStories(category: String): List<Article> {
        val response = api.getTopStories(category, "Yk4fxNYpK1WVADEEgnkgj89GdoDdmX2s")
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Failed to fetch news")
        }
    }
}
