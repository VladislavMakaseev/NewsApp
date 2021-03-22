package com.vi.newsapp.domain.articles

import com.vi.newsapp.domain.InputUseCase
import com.vi.newsapp.domain.pojo.Article

class AddArticles(
    private val articlesDataSource: ArticlesDataSource
) : InputUseCase<AddArticles.Params> {

    override suspend fun execute(params: Params) {
        articlesDataSource.addArticles(params.articles)
    }

    class Params(
        val articles: List<Article>
    )

}