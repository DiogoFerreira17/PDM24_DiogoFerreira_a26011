import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diogo.news.domain.model.Article
import com.diogo.news.presentation.view_Model.ArticleListViewModel
import android.text.Html
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

@Composable
fun ArticleListScreen(viewModel: ArticleListViewModel, onArticleClick: (Int) -> Unit) {
    val news = viewModel.news.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    if (news.isEmpty()) {
        LoadingScreen()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(news) { article ->
                ArticleBox(article = article, onClick = { onArticleClick(article.id) })
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Centraliza o texto
    ) {
        Text(text = "A carregar noticias...")
    }
}

// Remove tags HTML presentes (summary)
fun parseHtmlToText(html: String): AnnotatedString {
    return buildAnnotatedString {
        append(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString())
    }
}

@Composable
fun ArticleBox(article: Article, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )
            if (!article.summary.isNullOrBlank()) {
                Text(
                    text = parseHtmlToText(article.summary),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp),
                    maxLines = 3
                )
            }
        }
    }
}


