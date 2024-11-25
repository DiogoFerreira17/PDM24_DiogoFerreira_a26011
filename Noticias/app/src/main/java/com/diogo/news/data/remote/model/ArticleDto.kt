package com.diogo.news.data.remote.model

import com.diogo.news.domain.model.Article
import com.google.gson.annotations.SerializedName

data class ArticleDto(

    @SerializedName("id")
    val id: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("title")
    val title: String,
){
    fun toArticle(): Article {
        return Article(id = id,title = title,summary = summary?: "Sumario indisponivel", language = language)
    }
}