package com.example.calculadora.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import com.example.calculadora.logic.CalculatorLogic
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

val backgroundColor = Color(0xFF212121)
val buttonColor = Color(0xFF424242)
val displayColor = Color(0xFFB0BEC5)
val textColor = Color.White
val redPastel = Color(0xFFFF6F61)

@Composable
fun CalculatorScreen() {
    val logic = remember { CalculatorLogic() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalculatorDisplay(displayText = logic.displayText)

        Spacer(modifier = Modifier.height(20.dp))

        val buttons = listOf(
            listOf("7", "8", "9", "÷"),
            listOf("4", "5", "6", "x"),
            listOf("1", "2", "3", "-"),
            listOf("0", ".", "=", "+"),
            listOf("C")
        )

        buttons.forEach { row ->
            RowButtonRow(
                buttonLabels = row,
                onButtonClick = { label -> logic.onButtonClick(label) }
            )
        }
    }
}

@Composable
fun CalculatorDisplay(displayText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(displayColor, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = displayText,
            fontSize = 64.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun RowButtonRow(buttonLabels: List<String>, onButtonClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        buttonLabels.forEach { label ->
            CalculatorButton(
                label = label,
                isRed = label == "C",
                onClick = { onButtonClick(label) }
            )
        }
    }
}

@Composable
fun CalculatorButton(label: String, isRed: Boolean = false, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(85.dp)
            .padding(6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isRed) redPastel else buttonColor,
            contentColor = textColor
        )
    ) {
        Text(
            text = label,
            fontSize = 28.sp
        )
    }
}
