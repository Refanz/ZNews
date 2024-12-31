package com.refanzzzz.znews.ui.screens.news_source_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.znews.data.model.NewsSource
import com.refanzzzz.znews.data.repository.NewsSourceRepository
import com.refanzzzz.znews.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourceViewModel @Inject constructor(private val newsSourceRepository: NewsSourceRepository): ViewModel() {

    private val _newsSource: MutableState<ApiState<NewsSource>?> = mutableStateOf(null)
    val newsSource: State<ApiState<NewsSource>?> = _newsSource

    private fun getNewsSource(category: String) {
        viewModelScope.launch {
                newsSourceRepository.getNewsSource(category).onStart {
                _newsSource.value = ApiState.Loading
            }.catch {
                _newsSource.value = ApiState.Error(it.message.toString())
            }.collect {
                _newsSource.value = ApiState.Success(it)
            }
        }
    }
}