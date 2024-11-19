package com.diogo.news.data.remote.api

import ArticleDetailDto
import com.diogo.news.data.remote.model.ArticleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleApi{
    @GET("search-news")
    suspend fun getNews(
        @Query("text") text: String,
        @Query("language") language: String,
        @Query("api-key") apiKey: String
    ): List<ArticleDto>

    @GET("search-news/{id}")
    suspend fun getArticleDetail(
        @Path("id") articleId: Int,
        @Query("api-key") apiKey: String
    ):ArticleDetailDto
}