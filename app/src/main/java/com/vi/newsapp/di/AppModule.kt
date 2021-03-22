package com.vi.newsapp.di

import android.content.Context
import com.vi.newsapp.app.AppRepository
import com.vi.newsapp.domain.app.AppDataSource
import org.koin.android.ext.koin.androidContext
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

}