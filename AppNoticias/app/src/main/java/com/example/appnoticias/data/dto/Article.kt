package com.example.appnoticias.data.dto

data class Article(
    val title: String,
    val abstract: String?, // Pode ser null, por isso utilizamos o operador "?"
    val byline: String?,
    val published_date: String,
    val url: String
)
