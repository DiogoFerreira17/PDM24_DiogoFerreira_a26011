package com.diogo.news.data.remote.model
import com.google.gson.annotations.SerializedName

data class ApiResponseDetails(
    @SerializedName("news")
    val news: List<ArticleDetailDto>
)