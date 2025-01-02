package com.refanzzzz.znews.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.refanzzzz.znews.data.model.ArticlesItem
import com.refanzzzz.znews.utils.convertDateFormat
import com.refanzzzz.znews.utils.toStandardString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleItem(
    articleItem: ArticlesItem,
    onGoToArticleDetailScreen: (articleUrl: String) -> Unit,
    showImage: Boolean = true
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (showImage) {
                AsyncImage(
                    model = articleItem.urlToImage,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(articleItem.publishedAt?.convertDateFormat() ?: "")
                    Text("/")
                    Text(articleItem.author ?: "Admin")
                }
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = articleItem.title ?: ""
                )
                Text(
                    text = articleItem.content?.toStandardString() ?: ""
                )

                Button(
                    onClick = {
                        onGoToArticleDetailScreen(articleItem.url ?: "")
                    }
                ) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Read More",
                    )
                }
            }
        }
    }
}