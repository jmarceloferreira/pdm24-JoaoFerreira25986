package com.example.appnoticias.data.dto

data class Article(
    val title: String,
    val abstract: String?,
    val byline: String?,
    val published_date: String,
    val url: String,
    val multimedia: List<Multimedia>? // Adicionando o campo multimedia
)

data class Multimedia(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int,
    val caption: String?
)
