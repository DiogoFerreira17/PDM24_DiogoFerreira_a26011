package com.diogo.news.data.remote.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("top_news")
    val topNews: List<TopNew>
)