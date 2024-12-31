package com.refanzzzz.znews.ui.screen.news_source_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.refanzzzz.znews.data.model.NewsSource
import com.refanzzzz.znews.data.model.SourceItem
import com.refanzzzz.znews.ui.component.BaseScaffold
import com.refanzzzz.znews.ui.component.Loading
import com.refanzzzz.znews.utils.ApiState

@Composable
fun NewsSourceScreen(
    category: String,
    onGoToNewsSourceArticleScreen: () -> Unit,
    onGoBack: () -> Unit
) {
    val newsSourceViewModel = hiltViewModel<NewsSourceViewModel>()

    LaunchedEffect(Unit) {
        newsSourceViewModel.getNewsSource(category)
    }

    BaseScaffold(
        title = "News Sources",
        isBack = true,
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

                is ApiState.Error -> Log.e("NewsSourceScreen", result.error)

                is ApiState.Success -> NewsSourceList(result.data)
            }
        }
    }
}

@Composable
fun NewsSourceList(newsSource: NewsSource) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .background(color = Color.White)
    ) {
        items(
            items = newsSource.sources,
        ) { item ->
            NewsSourceCardItem(item)
        }
    }
}

@Composable
fun NewsSourceCardItem(newsSourceItem: SourceItem) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = CardDefaults.elevatedCardColors().contentColor,
            disabledContentColor = CardDefaults.elevatedCardColors().disabledContentColor,
            disabledContainerColor = CardDefaults.elevatedCardColors().disabledContainerColor
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = newsSourceItem.name ?: "BBC News"
            )
            Text(
                fontSize = 16.sp,
                text = newsSourceItem.description
                    ?: "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com."
            )
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = newsSourceItem.category?.uppercase() ?: "Health".uppercase(),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )
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
            NewsSourceCardItem(SourceItem())
        }
    }
}

