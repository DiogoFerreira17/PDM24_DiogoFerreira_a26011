package com.diogo.news.presentation.view_Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogo.news.data.remote.api.RetrofitInstance
import com.diogo.news.data.repository.ArticleRepositoryImpl
import com.diogo.news.domain.model.ArticleDetail
import com.diogo.news.domain.use_case.GetArticleDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel : ViewModel(){

    private val api= RetrofitInstance.api
    private val articleRepository = ArticleRepositoryImpl(api)
    private val getArticleDetailUseCase = GetArticleDetailUseCase(articleRepository)

    val articleDetail = MutableStateFlow<ArticleDetail?>(null)

    fun fetchArticleDetail(articleId: Int){
        viewModelScope.launch {
            try {
                articleDetail.value = getArticleDetailUseCase(articleId)
            } catch (e: Exception){
                articleDetail.value = null
            }
        }
    }
}