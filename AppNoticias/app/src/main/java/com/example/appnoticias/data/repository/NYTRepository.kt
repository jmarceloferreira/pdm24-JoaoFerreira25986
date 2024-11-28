package com.example.appnoticias.domain.repository

import com.example.appnoticias.data.dto.Article

interface NYTRepository {
    suspend fun getTopStories(category: String): List<Article>
}
