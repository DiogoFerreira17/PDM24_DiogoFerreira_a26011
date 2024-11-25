package com.diogo.news.domain.use_case

import android.util.Log
import com.diogo.news.domain.model.Article
import com.diogo.news.domain.model.ArticleDetail
import com.diogo.news.domain.repository.ArticleRepository

class GetNewsUseCase(private val repository: ArticleRepository){
    suspend operator fun invoke(): List<Article>{
        return repository.getNews()
    }
}

class GetArticleDetailUseCase(private val repository: ArticleRepository){
    suspend operator fun invoke(articleId: Int): ArticleDetail {
        return repository.getArticleDetail(articleId)
    }
}