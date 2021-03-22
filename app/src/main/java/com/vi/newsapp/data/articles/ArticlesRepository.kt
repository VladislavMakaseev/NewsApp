package com.vi.newsapp.data.articles

import com.vi.newsapp.domain.articles.ArticlesDataSource
import com.vi.newsapp.domain.pojo.Article
import com.vi.newsapp.domain.pojo.Example
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ArticlesRepository(
    private val api: Api,
    private val articlesDao: ArticlesDao,
    private val articleMapper: ArticleMapper
) : ArticlesDataSource {

    override suspend fun loadArticles(): List<Article> {
        articlesDao.clear()
        return suspendCoroutine { coroutine ->
            val call = api.getArticles()
            call.enqueue(object : Callback<Example> {
                override fun onResponse(
                    call: Call<Example>,
                    response: Response<Example>
                ) {
                    if (response.isSuccessful) {
                        val example = response.body()
                        example?.articles?.let { coroutine.resume(it) }
                    }
                }

                override fun onFailure(call: Call<Example>, t: Throwable) {
                    Timber.d("kek:: Error $t")
                }
            })
        }
    }

    override suspend fun addArticles(articles: List<Article>) {
        articles.let {
            val articleEntities = it.map { article ->
                ArticleEntity(
                    author = article.author ?: "",
                    title = article.title ?: "",
                    description = article.description ?: "",
                    url = article.url ?: "",
                    urlToImage = article.urlToImage ?: "",
                    publishedAt = article.publishedAt ?: "",
                    content = article.content ?: ""
                )
            }
            articlesDao.insert(articleEntities)
        }
    }

}