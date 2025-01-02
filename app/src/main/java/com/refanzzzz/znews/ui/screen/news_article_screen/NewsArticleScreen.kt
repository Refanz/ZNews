package com.refanzzzz.znews.ui.screen.news_article_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.refanzzzz.znews.data.model.ArticlesItem
import com.refanzzzz.znews.ui.component.BaseScaffold
import com.refanzzzz.znews.ui.component.ErrorView
import com.refanzzzz.znews.ui.component.Loading
import com.refanzzzz.znews.ui.component.NewsArticleItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleScreen(
    sourceId: String,
    onGoToArticleDetailScreen: (articleUrl: String) -> Unit,
    onGoSearch: (sourceId: String) -> Unit,
    onGoBack: () -> Unit
) {
    val newsArticleViewModel = hiltViewModel<NewsArticleViewModel>()
    val newsArticles =
        newsArticleViewModel.getNewsArticlesPagingDataSource(sourceId).collectAsLazyPagingItems()

    BaseScaffold(
        title = "News Articles",
        isBack = true,
        isSearch = true,
        onSearch = {
            onGoSearch(sourceId)
        },
        onBack = onGoBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NewsArticleList(newsArticles, onGoToArticleDetailScreen)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleList(
    newsArticles: LazyPagingItems<ArticlesItem>,
    onGoToArticleDetailScreen: (articleUrl: String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        if (newsArticles.itemCount == 0 && newsArticles.loadState.isIdle) {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillParentMaxSize()
                ) {
                    ErrorView("Articles is not found")
                }
            }
        }

        items(
            count = newsArticles.itemCount
        ) { index ->
            val article = newsArticles[index]
            NewsArticleItem(article!!, onGoToArticleDetailScreen)
        }

        newsArticles.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillParentMaxSize()
                        ) {
                            Loading()
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Loading()
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillParentMaxSize()
                        ) {
                            Text("Too many requests to the server")
                        }
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            ErrorView("Too many requests to the server")
                        }
                    }
                }
            }
        }
    }
}