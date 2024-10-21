package com.example.calculadora.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text // Certifique-se de importar o Text para exibição
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme

@Composable
fun CalculatorDisplay(displayText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Black),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = displayText,
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h4
        )
    }
}
