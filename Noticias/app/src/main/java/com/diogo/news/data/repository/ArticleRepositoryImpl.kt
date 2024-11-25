package com.diogo.news.data.repository

import android.util.Log
import com.diogo.news.data.remote.api.ArticleApi
import com.diogo.news.domain.model.Article
import com.diogo.news.domain.model.ArticleDetail
import com.diogo.news.domain.repository.ArticleRepository

class ArticleRepositoryImpl(private val api: ArticleApi) : ArticleRepository {

    private val apiKey = "2459fc61f94545c78fe425d5418fa5ea"

    override suspend fun getNews(): List<Article> {
        val response = api.getNews(
            sourceCountry = "us",
            language = "en",
            date = "2024-05-29",
            apiKey = apiKey
        )
        val topNews = response.topNews.flatMap { it.news }
        return topNews.map { it.toArticle() }
    }


    override suspend fun getArticleDetail(articleId: Int): ArticleDetail {
        return api.getArticleDetail(
            articleId = articleId,
            apiKey = apiKey
        ).toArticleDetail()
    }
}
