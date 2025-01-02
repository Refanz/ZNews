package com.refanzzzz.znews.ui.screen.news_source_search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.refanzzzz.znews.ui.component.BaseScaffold
import com.refanzzzz.znews.ui.component.Loading
import com.refanzzzz.znews.ui.component.NewsSourceCardItem
import com.refanzzzz.znews.ui.component.SearchForm

@Composable
fun NewsSourceSearchScreen(
    onGoToNewsSourceArticleScreen: (sourceId: String) -> Unit,
    onGoBack: () -> Unit
) {

    val newsSourceViewModel = hiltViewModel<NewsSourceSearchViewModel>()
    val searchText by newsSourceViewModel.searchText.collectAsState()
    val isSearching by newsSourceViewModel.isSearching.collectAsState()
    val newsSources by newsSourceViewModel.newsSources.collectAsState()

    BaseScaffold(
        title = "Search News Sources",
        isBack = true,
        onBack = onGoBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchForm(
                placeholder = "Search news source by name..",
                searchText = searchText,
                onSearchTextChange = newsSourceViewModel::onSearchTextChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isSearching) {
                Loading()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(newsSources) { source ->
                        NewsSourceCardItem(
                            source, onGoToNewsSourceArticleScreen
                        )
                    }
                }
            }
        }
    }
}