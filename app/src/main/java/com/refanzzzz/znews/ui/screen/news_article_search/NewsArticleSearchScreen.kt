package com.refanzzzz.znews.ui.screen.news_article_search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.refanzzzz.znews.ui.component.BaseScaffold
import com.refanzzzz.znews.ui.component.Loading
import com.refanzzzz.znews.ui.component.SearchForm
import com.refanzzzz.znews.ui.screen.news_article_screen.NewsArticleItem

@SuppressLint("NewApi")
@Composable
fun NewsArticleSearchScreen(
    sourceId: String,
    onBack: () -> Unit
) {

    val newsArticleSearchViewModel = hiltViewModel<NewsArticleSearchViewModel>()
    val searchText by newsArticleSearchViewModel.searchText.collectAsState()
    val newsArticles = newsArticleSearchViewModel.newsArticles.collectAsLazyPagingItems()
    val isSearching by newsArticleSearchViewModel.isSearching.collectAsState()

    BaseScaffold(
        title = "Search News Article",
        onBack = onBack,
        isBack = true
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchForm(searchText, newsArticleSearchViewModel::onSearchTextChange)

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
                    items(
                        count = newsArticles.itemCount
                    ) { index ->
                        val article = newsArticles[index]
                        NewsArticleItem(article!!) { }
                    }
                }
            }
        }
    }
}