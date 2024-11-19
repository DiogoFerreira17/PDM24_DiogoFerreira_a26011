package com.diogo.news.data.remote.model

import com.diogo.news.domain.model.Article

data class ArticleDto(
    val id: Int,
    val title: String,
    val text: String,
    val summary: String,
    val category: String
){
    fun toArticle(): Article {
        return Article(id=id, title=title,text=text,summary=summary,category=category)
    }
}