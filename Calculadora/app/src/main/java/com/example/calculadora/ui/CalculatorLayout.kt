package com.example.calculadora.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculadora.logic.*

@Composable
fun CalculatorLayout(modifier: Modifier = Modifier) {
    // Estados necessários para o funcionamento da calculadora
    var displayText by remember { mutableStateOf("0") }
    var memoryValue by remember { mutableStateOf(0.0) }
    var currentOperation by remember { mutableStateOf<Char?>(null) }
    var currentInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display da calculadora
        CalculatorDisplay(displayText)

        Spacer(modifier = Modifier.height(35.dp))

        // Linhas de botões da calculadora
        RowButtonRow(listOf("MRC", "M-", "M+", "ON"), redButton = 3, onClick = { button ->
            handleMemoryOperations(button, displayText, memoryValue) { updatedMemory, updatedDisplay ->
                memoryValue = updatedMemory
                displayText = updatedDisplay
            }
        })

        RowButtonRow(listOf("√", "%", "+/-", "C"), redButton = 3, onClick = { button ->
            handleSpecialOperations(button, displayText) { updatedDisplay ->
                displayText = updatedDisplay
            }
        })

        RowButtonRow(listOf("7", "8", "9", "÷"), onClick = { button ->
            handleInput(button, displayText, currentOperation, currentInput, result) { updatedDisplay, updatedOperation, updatedResult ->
                displayText = updatedDisplay
                currentOperation = updatedOperation
                result = updatedResult
            }
        })

        RowButtonRow(listOf("4", "5", "6", "*"), onClick = { button ->
            handleInput(button, displayText, currentOperation, currentInput, result) { updatedDisplay, updatedOperation, updatedResult ->
                displayText = updatedDisplay
                currentOperation = updatedOperation
                result = updatedResult
            }
        })

        RowButtonRow(listOf("1", "2", "3", "-"), onClick = { button ->
            handleInput(button, displayText, currentOperation, currentInput, result) { updatedDisplay, updatedOperation, updatedResult ->
                displayText = updatedDisplay
                currentOperation = updatedOperation
                result = updatedResult
            }
        })

        RowButtonRow(listOf("0", ".", "=", "+"), onClick = { button ->
            handleInput(button, displayText, currentOperation, currentInput, result) { updatedDisplay, updatedOperation, updatedResult ->
                displayText = updatedDisplay
                currentOperation = updatedOperation
                result = updatedResult
            }
        })
    }
}
