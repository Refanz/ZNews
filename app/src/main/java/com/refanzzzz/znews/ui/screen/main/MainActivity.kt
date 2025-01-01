package com.refanzzzz.znews.ui.screen.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.refanzzzz.znews.navigation.NavigationScreen
import com.refanzzzz.znews.ui.theme.ZNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZNewsTheme {
               NavigationScreen()
            }
        }
    }
}

