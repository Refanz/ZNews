package com.refanzzzz.znews.ui.screen.news_article_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinnzou.web.LoadingState
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewNavigator
import com.kevinnzou.web.rememberWebViewState
import com.refanzzzz.znews.ui.component.BaseScaffold

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsArticleDetailScreen(
    articleUrl: String,
    onGoBack: () -> Unit
) {
    val state = rememberWebViewState(articleUrl)
    val navigator = rememberWebViewNavigator()

    BaseScaffold(
        title = "Article",
        isBack = true,
        onBack = onGoBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val loadingState = state.loadingState
            if (loadingState is LoadingState.Loading) {
                LinearProgressIndicator(
                    progress = { loadingState.progress },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            WebView(
                state = state,
                navigator = navigator,
                onCreated = {
                    it.settings.javaScriptEnabled = true
                }
            )
        }
    }
}