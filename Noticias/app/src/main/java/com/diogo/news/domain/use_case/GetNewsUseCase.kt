package com.diogo.news.domain.use_case

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
        //Log.e("VER.ID",articleId.toString())
        return repository.getArticleDetail(articleId)
    }
}