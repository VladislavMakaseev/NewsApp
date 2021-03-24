package com.vi.newsapp.presentation.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.newsapp.base.event.Event
import com.vi.newsapp.domain.articles.Article
import com.vi.newsapp.domain.articles.GetArticlesByDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArticlesViewModel(
    private val getArticlesByDate: GetArticlesByDate
) : ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = _loadingLiveData as LiveData<Boolean>

    private val _articlesLiveData = MutableLiveData<List<Article>>()
    val articlesLiveData = _articlesLiveData as LiveData<List<Article>>

    private val _errorLiveData = MutableLiveData<Event<String?>>()
    val errorLiveData = _errorLiveData as LiveData<Event<String?>>

    init {
        loadItems()
    }

    private fun loadItems() {
        emitLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val items = getArticlesByDate.execute()
                emitItems(items)
            } catch (t: Throwable) {
                Timber.e(t)
                emitError(t)
            }
            emitLoading(false)
        }
    }

    private fun emitLoading(isLoading: Boolean) {
        _loadingLiveData.postValue(isLoading)
    }

    private fun emitItems(items: List<Article>) {
        _articlesLiveData.postValue(items)
    }

    private fun emitError(t: Throwable) {
        _errorLiveData.postValue(Event(t.message))
    }

}
