package com.example.appnoticias.presentation.newslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter // Importação correta

@Composable
fun NewsItem(title: String, description: String, imageUrl: String?, onClick: () -> Unit) {
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
            modifier = Modifier
                .padding(bottom = 4.dp)
                .clickable { onClick() } // Tornando o título clicável
        )

        // Descrição
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        // Exibir a imagem, caso haja URL fornecida
        imageUrl?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(200.dp) // Ajuste a altura conforme necessário
            )
        }
    }
}
