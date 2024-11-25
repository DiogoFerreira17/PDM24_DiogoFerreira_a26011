package com.diogo.news

import ArticleListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diogo.news.presentation.screen.ArticleDetailScreen
import com.diogo.news.presentation.view_Model.ArticleDetailViewModel
import com.diogo.news.presentation.view_Model.ArticleListViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var selectedArticleId by remember { mutableStateOf<Int?>(null) }

    if (selectedArticleId == null) {
        val articleListViewModel: ArticleListViewModel = viewModel()
        ArticleListScreen(articleListViewModel) { articleId ->
            selectedArticleId = articleId
        }
    } else {
        val articleDetailViewModel: ArticleDetailViewModel = viewModel()
        ArticleDetailScreen(articleDetailViewModel) {
            selectedArticleId = null
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}