package com.refanzzzz.znews.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.refanzzzz.znews.data.local.StaticDataSource
import com.refanzzzz.znews.data.model.NewsCategory

@Composable
fun MainScreen(
     onGoToNewsSourceScreen: (category: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NewsCategoryList(onGoToNewsSourceScreen)
    }
}

@Composable
fun NewsCategoryList(onGoToNewsSourceScreen: (category: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .background(color = Color.White)
    ) {
        items(items = StaticDataSource.getNewsCategories()) { item ->
            NewsCategoryCard(item, onGoToNewsSourceScreen)
        }
    }
}

@Composable
fun NewsCategoryCard(category: NewsCategory, onGoToNewsSourceScreen: (category: String) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = Color.White),
        onClick = {
            onGoToNewsSourceScreen(category.name)
        }
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            Image(
                painter = painterResource(category.image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                color = Color.Black,
                text = category.name.capitalize(LocaleList.current),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}