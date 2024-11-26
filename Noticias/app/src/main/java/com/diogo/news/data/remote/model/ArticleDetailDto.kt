package com.diogo.news.data.remote.model

import com.diogo.news.domain.model.ArticleDetail
import com.google.gson.annotations.SerializedName

data class ArticleDetailDto(
    @SerializedName("author")
    val author: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("title")
    val title: String,
){
    fun toArticleDetail(): ArticleDetail {
        return ArticleDetail(id=id, title=title, author = author, text = text, publishDate = publishDate)
    }
}