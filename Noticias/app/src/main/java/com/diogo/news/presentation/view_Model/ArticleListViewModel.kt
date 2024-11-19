package com.diogo.news.presentation.view_Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogo.news.data.remote.api.RetrofitInstance
import com.diogo.news.data.repository.ArticleRepositoryImpl
import com.diogo.news.domain.model.Article
import com.diogo.news.domain.use_case.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticleListViewModel : ViewModel(){

    private val api= RetrofitInstance.api
    private val repository = ArticleRepositoryImpl(api)
    private val getNewsUseCase = GetNewsUseCase(repository)

    val news = MutableStateFlow<List<Article>>(emptyList())

    fun fetchNews(){
        viewModelScope.launch {
            try {
                news.value = getNewsUseCase()
            } catch (e: Exception){
                news.value = emptyList()
            }
        }
    }
}