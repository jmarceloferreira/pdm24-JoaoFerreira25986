package com.example.calculadora.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import com.example.calculadora.logic.CalculatorLogic

@Composable
fun CalculatorLayout(modifier: Modifier = Modifier, logic: CalculatorLogic = CalculatorLogic()) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = logic.displayText,
            fontSize = 48.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(35.dp))
        val buttons = listOf(
            listOf("MRC", "M-", "M+", "ON"),
            listOf("√", "%", "+/-", "C"),
            listOf("7", "8", "9", "÷"),
            listOf("4", "5", "6", "x"),
            listOf("1", "2", "3", "-"),
            listOf("0", ".", "=", "+")
        )
        buttons.forEach { row ->
            RowButtonRow(
                buttonLabels = row,
                redButton = if (row.contains("C")) row.indexOf("C") else -1,
                onButtonClick = { label -> logic.onButtonClick(label) } // Integra a lógica do clique
            )
        }
    }
}
