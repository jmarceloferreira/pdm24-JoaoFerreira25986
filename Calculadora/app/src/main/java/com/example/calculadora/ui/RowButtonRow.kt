package com.example.calculadora.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button // Importe o componente Button corretamente
import androidx.compose.material.Text // Importe o componente Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowButtonRow(
    buttons: List<String>,
    redButton: Int = -1,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        buttons.forEachIndexed { index, text ->
            val buttonColor = if (index == redButton) Color.Red else Color.Gray
            ButtonComponent(text, color = buttonColor, onClick = { onClick(text) })
        }
    }
}

@Composable
fun ButtonComponent(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(80.dp, 80.dp)
    ) {
        Text(text = text, color = color)
    }
}
