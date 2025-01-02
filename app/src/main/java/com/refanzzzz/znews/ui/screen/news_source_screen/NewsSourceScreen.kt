package com.refanzzzz.znews.ui.screen.news_source_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.refanzzzz.znews.data.model.NewsSource
import com.refanzzzz.znews.data.model.SourceItem
import com.refanzzzz.znews.ui.component.BaseScaffold
import com.refanzzzz.znews.ui.component.ErrorView
import com.refanzzzz.znews.ui.component.Loading
import com.refanzzzz.znews.ui.component.NewsSourceCardItem
import com.refanzzzz.znews.utils.ApiState

@Composable
fun NewsSourceScreen(
    category: String,
    onGoToNewsSourceArticleScreen: (sourceId: String) -> Unit,
    onGoBack: () -> Unit,
    onGoSearch: () -> Unit
) {
    val newsSourceViewModel = hiltViewModel<NewsSourceViewModel>()

    LaunchedEffect(Unit) {
        newsSourceViewModel.getNewsSource(category)
    }

    BaseScaffold(
        title = "News Sources",
        isBack = true,
        isSearch = true,
        onSearch = {
            onGoSearch()
        },
        onBack = {
            onGoBack()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val result = newsSourceViewModel.newsSource.value) {
                is ApiState.Loading -> Loading()

                is ApiState.Error -> ErrorView("Too many requests to the server")

                is ApiState.Success -> {
                    if (result.data.sources.isEmpty()) ErrorView("News Sources is Not Found")
                    NewsSourceList(result.data, onGoToNewsSourceArticleScreen)
                }
            }
        }
    }
}

@Composable
fun NewsSourceList(newsSource: NewsSource, onGoToNewsSourceArticleScreen: (sourceId: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = newsSource.sources,
        ) { item ->
            NewsSourceCardItem(item, onGoToNewsSourceArticleScreen)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewNewsSourceScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            NewsSourceCardItem(SourceItem()) {

            }
        }
    }
}

