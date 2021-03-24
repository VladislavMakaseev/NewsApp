package com.vi.newsapp.domain.articles

import com.vi.newsapp.domain.InputOutputUseCase

class GetArticleById(
    private val articlesDataSource: ArticlesDataSource
) : InputOutputUseCase<GetArticleById.Params, Article> {

    override suspend fun execute(params: Params): Article {
        return articlesDataSource.getArticle(params.id)
    }

    class Params(
        val id: Long
    )

}