package com.vi.newsapp.domain.articles

import com.vi.newsapp.domain.pojo.Article

interface ArticlesDataSource {

    suspend fun loadArticles(): List<Article>

    suspend fun addArticles(articles: List<Article>)

}