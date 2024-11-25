package com.diogo.news.domain.model

import org.intellij.lang.annotations.Language

data class Article(
    val id: Int,
    val title: String,
    val language: String,
    val summary: String?
)