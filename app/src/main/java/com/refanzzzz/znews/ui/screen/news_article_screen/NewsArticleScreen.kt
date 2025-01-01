package com.refanzzzz.znews.ui.screen.news_article_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.refanzzzz.znews.data.model.ArticlesItem
import com.refanzzzz.znews.ui.component.BaseScaffold
import com.refanzzzz.znews.ui.component.ErrorView
import com.refanzzzz.znews.ui.component.Loading
import com.refanzzzz.znews.utils.convertDateFormat
import com.refanzzzz.znews.utils.toStandardString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleScreen(
    sourceId: String,
    onGoToArticleDetailScreen: (articleUrl: String) -> Unit,
    onGoBack: () -> Unit
) {
    val newsArticleViewModel = hiltViewModel<NewsArticleViewModel>()
    val newsArticles =
        newsArticleViewModel.getNewsArticlesPagingDataSource(sourceId).collectAsLazyPagingItems()

    BaseScaffold(
        title = "News Article",
        isBack = true,
        onBack = onGoBack,
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
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Loading()
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorView("News Articles is not found")
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        ErrorView("Too many requests to the server")
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleItem(
    articleItem: ArticlesItem,
    onGoToArticleDetailScreen: (articleUrl: String) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                model = articleItem.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(articleItem.publishedAt?.convertDateFormat() ?: "")
                    Text("/")
                    Text(articleItem.author ?: "Admin")
                }
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = articleItem.title ?: ""
                )
                Text(
                    text = articleItem.content?.toStandardString() ?: ""
                )

                Button(
                    onClick = {
                        onGoToArticleDetailScreen(articleItem.url ?: "")
                    }
                ) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Read More",
                    )
                }
            }
        }
    }
}