import com.diogo.news.domain.model.ArticleDetail

data class ArticleDetailDto(
    val id: Int,
    val title: String,
    val text: String,
    val summary: String,
    val category: String
){
    fun toArticleDetail(): ArticleDetail {
        return ArticleDetail(id=id, title=title,text=text,summary=summary,category=category)
    }
}