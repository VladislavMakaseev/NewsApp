package com.vi.newsapp.data.articles

import com.vi.newsapp.domain.articles.Article

class ArticleMapper {

    fun toArticle(entity: ArticleEntity) = Article(
        id = entity.id ?: 0,
        author = entity.author,
        title = entity.title,
        description = entity.description,
        url = entity.url,
        urlToImage = entity.urlToImage,
        publishedAt = entity.publishedAt,
        content = entity.content
    )

}