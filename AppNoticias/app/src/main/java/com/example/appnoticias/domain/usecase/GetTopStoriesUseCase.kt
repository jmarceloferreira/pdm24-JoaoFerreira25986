    package com.example.appnoticias.domain.usecase

    import com.example.appnoticias.data.dto.Article
    import com.example.appnoticias.domain.repository.NYTRepository

    class GetTopStoriesUseCase(private val repository: NYTRepository) {
        suspend operator fun invoke(category: String): List<Article> {
            return repository.getTopStories(category)
        }
    }
