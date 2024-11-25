package com.diogo.news.data.remote.model

import com.diogo.news.domain.model.ArticleDetail

data class ArticleDetailDto(
    val id: Int,
    val title: String,
    val language: String,
    val summary: String
){
    fun toArticleDetail(): ArticleDetail {
        return ArticleDetail(id=id, title=title,language=language, summary = summary)
    }
}