package com.vi.newsapp.di

import com.vi.newsapp.data.articles.ArticleMapper
import com.vi.newsapp.data.articles.ArticlesRepository
import com.vi.newsapp.data.articles.NetworkService
import com.vi.newsapp.data.db.AppDatabase
import com.vi.newsapp.domain.articles.ArticlesDataSource
import com.vi.newsapp.domain.articles.GetArticleById
import com.vi.newsapp.domain.articles.GetArticlesByDate
import com.vi.newsapp.domain.articles.LoadArticles
import com.vi.newsapp.presentation.articles.ArticlesViewModel
import com.vi.newsapp.presentation.detail.DetailArticleViewModel
import com.vi.newsapp.presentation.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleModule = module {

    single {
        NetworkService.createApi()
    }

    single<ArticlesDataSource> {
        ArticlesRepository(
            api = get(),
            articlesDao = get(),
            articleMapper = get()
        )
    }

    single {
        AppDatabase.create(androidContext())
    }

    single {
        (get() as AppDatabase).articlesDao()
    }

    single {
        ArticleMapper()
    }

    single {
        LoadArticles(
            articlesDataSource = get()
        )
    }

    single {
        GetArticlesByDate(
            articlesDataSource = get()
        )
    }

    single {
        GetArticleById(
            articlesDataSource = get()
        )
    }

    viewModel {
        ArticlesViewModel(
            getArticlesByDate = get()
        )
    }

    viewModel {
        SplashViewModel(
            loadArticles = get()
        )
    }

    viewModel { (id: Long) ->
        DetailArticleViewModel(
            id = id,
            getArticleById = get()
        )
    }

}