package com.refanzzzz.znews.ui.screen.news_article_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.znews.data.model.NewsArticle
import com.refanzzzz.znews.data.repository.NewsSourceRepository
import com.refanzzzz.znews.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(private val newsSourceRepository: NewsSourceRepository): ViewModel() {
    private val _newsArticles: MutableState<ApiState<NewsArticle>> = mutableStateOf(ApiState.Loading)
    val newsArticles: State<ApiState<NewsArticle>> = _newsArticles

    fun getNewsArticlesBySource(source: String) {
        viewModelScope.launch {
            newsSourceRepository.getNewsArticlesBySource(source).onStart {
                _newsArticles.value = ApiState.Loading
            }.catch {
                _newsArticles.value = ApiState.Error(it.message ?: "")
            }.collect {
                _newsArticles.value = ApiState.Success(it)
            }
        }
    }
}