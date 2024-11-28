package com.example.appnoticias.presentation.newslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NewsItem(title: String, description: String) {
    // Layout com coluna para o título e descrição
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp) // padding para o título
        )

        // Descrição
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
    }
}
