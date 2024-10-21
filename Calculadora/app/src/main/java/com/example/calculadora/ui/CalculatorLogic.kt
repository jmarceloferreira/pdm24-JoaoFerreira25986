package com.example.calculadora.ui

package com.example.calculadora.logic

import kotlin.math.sqrt

// Lógica para operações de memória
fun handleMemoryOperations(button: String, displayText: String, memoryValue: Double, onUpdate: (Double, String) -> Unit) {
    when (button) {
        "M+" -> onUpdate(memoryValue + displayText.toDouble(), displayText)
        "M-" -> onUpdate(memoryValue - displayText.toDouble(), displayText)
        "MRC" -> onUpdate(memoryValue, memoryValue.toString())
        "ON" -> onUpdate(0.0, "0")
    }
}

// Lógica para operações especiais (√, %, +/- e Clear)
fun handleSpecialOperations(button: String, displayText: String, onUpdate: (String) -> Unit) {
    when (button) {
        "√" -> onUpdate(sqrt(displayText.toDouble()).toString())
        "%" -> onUpdate((displayText.toDouble() / 100).toString())
        "+/-" -> onUpdate((-displayText.toDouble()).toString())
        "C" -> onUpdate("0")
    }
}

// Lógica para operações aritméticas e entrada de números
fun handleInput(
    button: String,
    displayText: String,
    currentOperation: Char?,
    currentInput: String,
    result: Double,
    onUpdate: (String, Char?, Double) -> Unit
) {
    when (button) {
        "+", "-", "*", "÷" -> {
            val operation = button.first()
            onUpdate(displayText, operation, displayText.toDouble())
        }
        "=" -> {
            val finalResult = performOperation(currentOperation, result, displayText.toDouble())
            onUpdate(finalResult.toString(), null, finalResult)
        }
        else -> {
            val updatedDisplay = if (displayText == "0") button else displayText + button
            onUpdate(updatedDisplay, currentOperation, result)
        }
    }
}

// Função que realiza a operação aritmética
fun performOperation(operation: Char?, operand1: Double, operand2: Double): Double {
    return when (operation) {
        '+' -> operand1 + operand2
        '-' -> operand1 - operand2
        '*' -> operand1 * operand2
        '÷' -> operand1 / operand2
        else -> operand2
    }
}
