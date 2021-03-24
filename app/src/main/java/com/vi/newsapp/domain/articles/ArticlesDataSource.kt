package com.vi.newsapp.domain.articles

interface ArticlesDataSource {

    suspend fun loadArticles(): Boolean

    suspend fun getArticles(): List<Article>

    suspend fun getArticle(id: Long): Article

}