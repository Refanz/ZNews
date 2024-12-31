package com.refanzzzz.znews.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.refanzzzz.znews.ui.screen.main_screen.MainScreen
import com.refanzzzz.znews.ui.screen.news_source_screen.NewsSourceScreen

@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination =  Screen.MainScreen,

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
                onGoBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}