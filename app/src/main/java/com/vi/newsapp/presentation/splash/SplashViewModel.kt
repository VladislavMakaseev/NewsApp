package com.vi.newsapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.newsapp.domain.articles.LoadArticles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashViewModel(
    private val loadArticles: LoadArticles
) : ViewModel() {

    private val _articlesAddedLiveData = MutableLiveData<Boolean>()
    val articlesAddedLiveData: LiveData<Boolean> = _articlesAddedLiveData

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isALoaded = loadArticles.execute()
                emitArticlesAdded(isALoaded)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    private fun emitArticlesAdded(isAdded: Boolean) {
        _articlesAddedLiveData.postValue(isAdded)
    }

}