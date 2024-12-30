package com.refanzzzz.znews.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.refanzzzz.znews.ui.theme.ZNewsTheme
import com.refanzzzz.znews.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZNewsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NewsSources(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun NewsSources(mainViewModel: MainViewModel) {
    when (val result = mainViewModel.newsSource.value) {
        is ApiState.Loading -> {
            CircularProgressIndicator()
        }

        is ApiState.Error -> {
            Text(result.error)
        }

        is ApiState.Success -> {
            LazyColumn {
                items(items = result.data.sources ?: listOf()) { data ->
                    Text(data.name.toString())
                }
            }
        }

        else -> {}
    }
}