package com.example.appnoticias.presentation.newslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.appnoticias.data.dto.Article
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsItem(article: Article, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imageUrl = article.multimedia?.firstOrNull()?.url ?: ""
        if (imageUrl.isNotEmpty()) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = "Imagem da notícia",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        val encodedTitle = URLEncoder.encode(article.title, StandardCharsets.UTF_8.toString())
                        val encodedDescription = URLEncoder.encode(article.abstract ?: "Sem descrição disponível", StandardCharsets.UTF_8.toString())
                        val encodedDate = URLEncoder.encode(article.published_date, StandardCharsets.UTF_8.toString())
                        val encodedImageUrl = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())

                        navController.navigate("details/$encodedTitle/$encodedDescription/$encodedDate/$encodedImageUrl")
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = article.abstract ?: "Sem descrição disponível",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
    }
}
