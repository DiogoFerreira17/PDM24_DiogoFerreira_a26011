package com.diogo.news.domain.model

data class Article(
    val id: Int,
    val title: String,
    val language: String,
    val summary: String?
)