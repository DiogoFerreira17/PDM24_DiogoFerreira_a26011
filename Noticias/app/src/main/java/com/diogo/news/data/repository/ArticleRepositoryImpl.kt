package com.diogo.news.data.repository

import com.diogo.news.data.remote.api.ArticleApi
import com.diogo.news.domain.model.Article
import com.diogo.news.domain.model.ArticleDetail
import com.diogo.news.domain.repository.ArticleRepository

class ArticleRepositoryImpl(private val api: ArticleApi): ArticleRepository {
    override suspend fun getNews():List<Article>{
        return api.getNews("%22Elon%20Musk%22%20-tesla","en","2459fc61f94545c78fe425d5418fa5ea").map{it.toArticle()}
    }

    override suspend fun getArticleDetail(articleId: Int): ArticleDetail {
        return api.getArticleDetail(articleId,"2459fc61f94545c78fe425d5418fa5ea").toArticleDetail()
    }
}