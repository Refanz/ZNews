package com.refanzzzz.znews.ui.screen.news_article_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.refanzzzz.znews.data.model.ArticlesItem
import com.refanzzzz.znews.data.model.NewsArticle
import com.refanzzzz.znews.ui.component.BaseScaffold
import com.refanzzzz.znews.ui.component.ErrorView
import com.refanzzzz.znews.ui.component.Loading
import com.refanzzzz.znews.utils.ApiState
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

    LaunchedEffect(Unit) {
        newsArticleViewModel.getNewsArticlesBySource(sourceId)
    }

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
            when (val result = newsArticleViewModel.newsArticles.value) {
                is ApiState.Loading -> Loading()

                is ApiState.Error -> ErrorView("News Articles is Not Found")

                is ApiState.Success -> NewsArticleList(result.data, onGoToArticleDetailScreen)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleList(newsArticle: NewsArticle, onGoToArticleDetailScreen: (articleUrl: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = newsArticle.articles
        ) { item ->
            NewsArticleItem(item, onGoToArticleDetailScreen)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleItem(articleItem: ArticlesItem, onGoToArticleDetailScreen: (articleUrl: String) -> Unit) {
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
                contentDescription = null
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
                    Text(articleItem.author ?: "")
                }
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = articleItem.title ?: ""
                )
                Text(
                    text = articleItem.content?.toStandardString() ?: ""
                )

                ElevatedButton(
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