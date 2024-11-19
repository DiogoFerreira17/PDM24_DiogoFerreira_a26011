package com.diogo.news.domain.model

data class Article(
    val id: Int,
    val title: String,
    val text: String,
    val summary: String,
    val category: String
)