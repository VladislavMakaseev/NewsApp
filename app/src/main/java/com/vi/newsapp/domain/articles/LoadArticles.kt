package com.vi.newsapp.domain.articles

import com.vi.newsapp.domain.OutputUseCase
import com.vi.newsapp.domain.pojo.Article

class LoadArticles(
    private val articlesDataSource: ArticlesDataSource
) : OutputUseCase<List<Article>> {

    override suspend fun execute(): List<Article> {
        return articlesDataSource.loadArticles()
    }

}