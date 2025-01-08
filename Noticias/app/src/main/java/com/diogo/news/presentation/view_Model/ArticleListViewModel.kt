package com.diogo.news.presentation.view_Model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogo.news.data.remote.api.RetrofitInstance
import com.diogo.news.data.repository.ArticleRepositoryImpl
import com.diogo.news.domain.model.Article
import com.diogo.news.domain.use_case.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleListViewModel : ViewModel(){

    private val api = RetrofitInstance.api
    private val articleRepository = ArticleRepositoryImpl(api)
    private val getNewsUseCase = GetNewsUseCase(articleRepository)
    private val _news = MutableStateFlow<List<Article>>(emptyList())

    val news: StateFlow<List<Article>> = _news

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                _news.value = getNewsUseCase()
                //Log.e("Articles fetched", articles.toString())
            } catch (e: Exception) {
                _news.value = emptyList()
            }
        }
    }

}