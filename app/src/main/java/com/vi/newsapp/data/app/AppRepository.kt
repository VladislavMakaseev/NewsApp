package com.vi.newsapp.data.app

import android.content.SharedPreferences
import com.vi.newsapp.domain.app.AppDataSource

class AppRepository(
    private val prefs: SharedPreferences
) : AppDataSource