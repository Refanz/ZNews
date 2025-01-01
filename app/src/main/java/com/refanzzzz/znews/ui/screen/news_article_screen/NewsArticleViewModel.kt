package com.refanzzzz.znews.ui.screen.news_article_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.refanzzzz.znews.data.model.ArticlesItem
import com.refanzzzz.znews.data.repository.NewsSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(private val newsSourceRepository: NewsSourceRepository): ViewModel() {
    fun getNewsArticlesPagingDataSource(source: String): Flow<PagingData<ArticlesItem>> {
        return newsSourceRepository.getNewsArticlesPagingDataSource(source).cachedIn(viewModelScope)
    }
}