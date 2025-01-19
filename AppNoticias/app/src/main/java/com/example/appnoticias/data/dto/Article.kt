package com.example.appnoticias.data.dto

data class Article(
    val title: String,
    val abstract: String?,
    val byline: String?,
    val published_date: String,
    val url: String,
    val multimedia: List<Multimedia>?
)

data class Multimedia(
    val url: String
)
