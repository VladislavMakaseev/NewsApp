package com.vi.newsapp.data.articles

import com.vi.newsapp.domain.articles.ArticlesDataSource
import com.vi.newsapp.domain.pojo.Article
import com.vi.newsapp.domain.pojo.Example
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.vi.newsapp.domain.articles.Article as DomainArticles

class ArticlesRepository(
    private val api: Api,
    private val articlesDao: ArticlesDao,
    private val articleMapper: ArticleMapper
) : ArticlesDataSource {

    override suspend fun loadArticles(): Boolean {
        val listOfArticle: List<Article> = suspendCoroutine { coroutine ->
            val call = api.getArticles()
            call.enqueue(object : Callback<Example> {
                override fun onResponse(
                    call: Call<Example>,
                    response: Response<Example>
                ) {
                    if (response.isSuccessful) {
                        val example = response.body()
                        example?.articles?.let {
                            coroutine.resume(it)
                        }
                    }
                }

                override fun onFailure(call: Call<Example>, t: Throwable) {
                    Timber.d("kek:: Error $t")
                }
            })
        }
        return addArticles(listOfArticle)
    }

    override suspend fun getArticles(): List<DomainArticles> {
        return articlesDao.getAll().map { articleMapper.toArticle(it) }
    }

    override suspend fun getArticle(id: Long): DomainArticles {
        return articleMapper.toArticle(articlesDao.getArticleById(id))
    }

    private suspend fun addArticles(articles: List<Article>): Boolean {
        articles.let {
            val articleEntities = it.map { article ->
                ArticleEntity(
                    author = article.author ?: "",
                    title = article.title ?: "",
                    description = article.description ?: "",
                    url = article.url ?: "",
                    urlToImage = article.urlToImage ?: "",
                    publishedAt = LocalDateTime.parse(
                        article.publishedAt,
                        DateTimeFormatter.ISO_DATE_TIME
                    ),
                    content = article.content ?: ""
                )
            }
            articlesDao.clear()
            articlesDao.insert(articleEntities)
            return articlesDao.count() > 0
        }
    }

}