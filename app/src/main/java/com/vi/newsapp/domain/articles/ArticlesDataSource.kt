package com.vi.newsapp.domain.articles

interface ArticlesDataSource {

    suspend fun loadArticles(): Boolean

}