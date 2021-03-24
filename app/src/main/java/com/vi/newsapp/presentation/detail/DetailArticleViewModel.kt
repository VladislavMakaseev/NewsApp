package com.vi.newsapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.newsapp.base.event.Event
import com.vi.newsapp.domain.articles.Article
import com.vi.newsapp.domain.articles.GetArticleById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailArticleViewModel(
    private val id: Long,
    private val getArticleById: GetArticleById
) : ViewModel() {

    private val _itemsLiveData = MutableLiveData<Article>()
    val itemsLiveData: LiveData<Article> = _itemsLiveData

    private val _errorLiveData = MutableLiveData<Event<String?>>()
    val errorLiveData: LiveData<Event<String?>> = _errorLiveData

    init {
        getArticleById()
    }

    private fun getArticleById() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val article = getArticleById.execute(GetArticleById.Params(id))
                emitItems(article)
            } catch (t: Throwable) {
                Timber.e(t)
                emitError(t)
            }
        }
    }

    private fun emitItems(article: Article) {
        _itemsLiveData.postValue(article)
    }

    private fun emitError(t: Throwable) {
        _errorLiveData.postValue(Event(t.message))
    }

}
