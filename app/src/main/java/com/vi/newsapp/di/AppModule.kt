package com.vi.newsapp.di

import android.content.Context
import com.vi.newsapp.data.app.AppRepository
import com.vi.newsapp.data.articles.ArticleMapper
import com.vi.newsapp.data.articles.ArticlesRepository
import com.vi.newsapp.data.articles.NetworkService
import com.vi.newsapp.data.db.AppDatabase
import com.vi.newsapp.domain.app.AppDataSource
import com.vi.newsapp.domain.articles.AddArticles
import com.vi.newsapp.domain.articles.ArticlesDataSource
import com.vi.newsapp.domain.articles.LoadArticles
import com.vi.newsapp.presentation.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val APP_PREFS_NAME = "app_prefs"

val appModule = module {

    single {
        androidContext().getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE)
    }

    single<AppDataSource> {
        AppRepository(
            prefs = get()
        )
    }

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
        AddArticles(
            articlesDataSource = get()
        )
    }

    viewModel {
        SplashViewModel(
            loadArticles = get(),
            addArticles = get()
        )
    }

}