package com.vi.newsapp.domain.articles

import com.vi.newsapp.domain.OutputUseCase

class LoadArticles(
    private val articlesDataSource: ArticlesDataSource
) : OutputUseCase<Boolean> {

    override suspend fun execute(): Boolean {
        return articlesDataSource.loadArticles()
    }

}