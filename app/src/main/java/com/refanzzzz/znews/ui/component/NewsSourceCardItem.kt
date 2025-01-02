package com.refanzzzz.znews.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.refanzzzz.znews.data.model.SourceItem

@Composable
fun NewsSourceCardItem(
    newsSourceItem: SourceItem,
    onGoToNewsSourceArticleScreen: (sourceId: String) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onGoToNewsSourceArticleScreen(newsSourceItem.id ?: "")
        }
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