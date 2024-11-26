package com.diogo.news.data.remote.api

import com.diogo.news.data.remote.model.ApiResponse
import com.diogo.news.data.remote.model.ApiResponseDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {
    @GET("top-news")
    suspend fun getNews(
        @Query("source-country") sourceCountry: String,
        @Query("language") language: String,
        @Query("date") date: String,
        @Query("api-key") apiKey: String
    ): ApiResponse

    @GET("retrieve-news")
    suspend fun getArticleDetail(
        @Query("ids") articleId: Int,
        @Query("api-key") apiKey: String
    ): ApiResponseDetails
}
