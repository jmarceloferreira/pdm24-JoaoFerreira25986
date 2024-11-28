package com.example.appnoticias.data.dto

data class NYTResponse(
    val status: String,
    val results: List<Article>
)
