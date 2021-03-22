package com.vi.newsapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.newsapp.domain.articles.AddArticles
import com.vi.newsapp.domain.articles.LoadArticles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashViewModel(
    private val loadArticles: LoadArticles,
    private val addArticles: AddArticles
) : ViewModel() {

    private val _articlesAddedLiveData = MutableLiveData<Boolean>()
    val articlesAddedLiveData = _articlesAddedLiveData as LiveData<Boolean>

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val articles = loadArticles.execute()
                addArticles.execute(AddArticles.Params(articles))
                emitArticlesAdded()
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    private fun emitArticlesAdded() {
        _articlesAddedLiveData.postValue(true)
    }

}