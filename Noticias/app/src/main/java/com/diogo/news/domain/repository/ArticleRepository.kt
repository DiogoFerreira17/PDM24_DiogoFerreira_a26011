package com.diogo.news.domain.repository

import com.diogo.news.domain.model.Article
import com.diogo.news.domain.model.ArticleDetail

interface ArticleRepository{
    suspend fun getNews(): List<Article>
    suspend fun getArticleDetail(articleId: Int): ArticleDetail
}