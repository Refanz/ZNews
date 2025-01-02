package com.refanzzzz.znews.ui.screen.news_article_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.refanzzzz.znews.data.model.ArticlesItem
import com.refanzzzz.znews.data.repository.NewsSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class NewsArticleSearchViewModel @Inject constructor(private val newsSourceRepository: NewsSourceRepository) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private val _newsArticles = MutableStateFlow<PagingData<ArticlesItem>>(PagingData.empty())
    val newsArticles = _newsArticles.asStateFlow()

    fun searchNewsArticles(source: String) = searchText
        .debounce(1000L)
        .onEach {
            _isSearching.update { true }
        }
        .flatMapLatest { text ->
            delay(2000)
            newsSourceRepository.searchNewsArticles(source, text)
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _newsArticles.value
        )
}