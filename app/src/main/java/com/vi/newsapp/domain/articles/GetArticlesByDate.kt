package com.vi.newsapp.domain.articles

import com.vi.newsapp.domain.OutputUseCase

class GetArticlesByDate(
    private val articlesDataSource: ArticlesDataSource
) : OutputUseCase<List<Article>> {

    override suspend fun execute(): List<Article> {
        return articlesDataSource.getArticles().sortedBy { it.publishedAt }
    }

}