package com.diogo.news.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ArticleApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://worldnewsapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticleApi::class.java)
    }
}