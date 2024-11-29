package com.example.appnoticias.presentation.newsdetail

import coil.compose.rememberImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appnoticias.data.dto.Article

@Composable
fun NewsDetailScreen(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = article.abstract ?: "Sem descrição disponível",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Exibindo a imagem principal se existir
        article.multimedia?.firstOrNull()?.let { multimedia ->
            Image(
                painter = rememberImagePainter(multimedia.url),
                contentDescription = multimedia.caption,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = "Publicado em: ${article.published_date}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
