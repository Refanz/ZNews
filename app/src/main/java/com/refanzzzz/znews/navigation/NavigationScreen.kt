package com.refanzzzz.znews.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.refanzzzz.znews.ui.screen.main_screen.MainScreen
import com.refanzzzz.znews.ui.screen.news_article_detail.NewsArticleDetailScreen
import com.refanzzzz.znews.ui.screen.news_article_screen.NewsArticleScreen
import com.refanzzzz.znews.ui.screen.news_source_screen.NewsSourceScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.MainScreen,

        ) {
        composable<Screen.MainScreen> {
            MainScreen(
                onGoToNewsSourceScreen = { category ->
                    navController.navigate(Screen.NewsSourceScreen(category))
                }
            )
        }

        composable<Screen.NewsSourceScreen> { backStackEntry ->
            val newsSourceScreen: Screen.NewsSourceScreen = backStackEntry.toRoute()

            NewsSourceScreen(
                category = newsSourceScreen.category,
                onGoToNewsSourceArticleScreen = { sourceId ->
                    navController.navigate(Screen.NewsArticleScreen(sourceId))
                },
                onGoBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screen.NewsArticleScreen> { backStackEntry ->
            val newsArticleScreen: Screen.NewsArticleScreen = backStackEntry.toRoute()

            NewsArticleScreen(
                sourceId = newsArticleScreen.sourceId,
                onGoToArticleDetailScreen = { articleUrl ->
                    navController.navigate(Screen.NewsArticleDetailScreen(articleUrl))
                },
                onGoBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screen.NewsArticleDetailScreen> { backStackEntry ->
            val newsArticleDetailScreen: Screen.NewsArticleDetailScreen = backStackEntry.toRoute()

            NewsArticleDetailScreen(
                articleUrl = newsArticleDetailScreen.articleUrl,
                onGoBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}