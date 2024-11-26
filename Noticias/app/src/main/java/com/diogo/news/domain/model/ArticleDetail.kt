package com.diogo.news.domain.model

data class ArticleDetail(
    val author: String,
    val id: Int,
    val publishDate: String,
    val text: String,
    val title: String,
)