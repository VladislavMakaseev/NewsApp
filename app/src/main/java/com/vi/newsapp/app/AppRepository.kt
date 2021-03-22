package com.vi.newsapp.app

import android.content.SharedPreferences
import com.vi.newsapp.domain.app.AppDataSource

class AppRepository(
    private val prefs: SharedPreferences
) : AppDataSource