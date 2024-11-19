package com.diogo.news.data.remote.api

object Retrofit {
    val api: ArticleApi by lazy
    {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticleApi::class.java)
    }
}