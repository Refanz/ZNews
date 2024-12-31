package com.refanzzzz.znews.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import com.refanzzzz.znews.navigation.NavigationScreen
import com.refanzzzz.znews.ui.theme.ZNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZNewsTheme {
                val navController = rememberNavController()

               Scaffold(topBar = {
                   TopAppBar(
                       colors = topAppBarColors(
                           containerColor = MaterialTheme.colorScheme.primaryContainer,
                           titleContentColor = MaterialTheme.colorScheme.primary
                       ),
                       title = {
                           Text(
                               fontWeight = FontWeight.Bold,
                               text = "ZNews"
                           )
                       },
                       navigationIcon = {
                           IconButton(
                               onClick = {
                                   navController.navigateUp()
                               }
                           ) {
                               Icon(
                                   imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                   contentDescription = null
                               )
                           }
                       }
                   )
               }) { innerPadding ->
                   NavigationScreen(
                       modifier = Modifier.padding(innerPadding)
                   )
               }
            }
        }
    }
}

