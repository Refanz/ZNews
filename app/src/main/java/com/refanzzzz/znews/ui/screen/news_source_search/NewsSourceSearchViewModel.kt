package com.refanzzzz.znews.ui.screen.news_source_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.znews.data.model.SourceItem
import com.refanzzzz.znews.data.repository.NewsSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class NewsSourceSearchViewModel @Inject constructor(private val newsSourceRepository: NewsSourceRepository) :
    ViewModel() {

    init {
        getNewsSource("")
    }

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _newsSources = MutableStateFlow<List<SourceItem>>(emptyList())
    val newsSources = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_newsSources) { text, newsSources ->
            if (text.isBlank()) {
                newsSources
            } else {
                delay(2000)
                newsSources.filter {
                    it.name!!.contains(text, ignoreCase = true)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _newsSources.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun getNewsSource(category: String) {
        viewModelScope.launch {
            newsSourceRepository.getNewsSource(category).collect {
                _newsSources.value = it.sources
            }
        }
    }
}