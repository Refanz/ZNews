package com.refanzzzz.znews.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable data object MainScreen: Screen
    @Serializable data class NewsSourceScreen(val category: String): Screen
    @Serializable data class NewsArticleScreen(val sourceId: String): Screen
    @Serializable data class NewsArticleDetailScreen(val articleUrl: String): Screen
}