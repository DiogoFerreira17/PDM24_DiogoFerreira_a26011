import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogo.news.data.remote.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel : ViewModel(){

    private val api= RetrofitInstance.api
    private val repository = ArticleRepositoryImpl(api)
    private val getArticleDetailUseCase = GetArticleDetailUseCase(repository)

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