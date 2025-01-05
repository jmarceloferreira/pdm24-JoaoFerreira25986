package com.example.calculadora.logic

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CalculatorLogic {
    var displayText by mutableStateOf("0")
        private set

    private var firstOperand: String = ""
    private var operator: String? = null
    private var secondOperand: String = ""

    private var isOperatorSelected = false

    fun onButtonClick(button: String) {
        when {
            button in "0123456789." -> handleNumberInput(button)
            button in listOf("+", "-", "x", "÷") -> handleOperatorInput(button)
            button == "=" -> calculateResult()
            button == "C" -> clear()
        }
    }

    private fun handleNumberInput(input: String) {
        if (!isOperatorSelected) {
            // Construindo o primeiro operando
            firstOperand = if (firstOperand == "0" && input != ".") input else firstOperand + input
            displayText = firstOperand
        } else {
            // Construindo o segundo operando
            secondOperand = if (secondOperand == "0" && input != ".") input else secondOperand + input
            displayText = secondOperand
        }
    }

    private fun handleOperatorInput(input: String) {
        if (firstOperand.isNotEmpty()) {
            operator = input
            isOperatorSelected = true // Prepara para o segundo operando
        }
    }

    private fun calculateResult() {
        if (firstOperand.isNotEmpty() && secondOperand.isNotEmpty() && operator != null) {
            val result = when (operator) {
                "+" -> firstOperand.toDouble() + secondOperand.toDouble()
                "-" -> firstOperand.toDouble() - secondOperand.toDouble()
                "x" -> firstOperand.toDouble() * secondOperand.toDouble()
                "÷" -> if (secondOperand.toDouble() != 0.0) {
                    firstOperand.toDouble() / secondOperand.toDouble()
                } else {
                    null // Evita divisão por zero
                }
                else -> null
            }

            displayText = result?.toString() ?: "Error"
            resetAfterCalculation(result?.toString())
        }
    }

    private fun resetAfterCalculation(result: String?) {
        firstOperand = result ?: ""
        secondOperand = ""
        operator = null
        isOperatorSelected = false
    }

    private fun clear() {
        displayText = "0"
        firstOperand = ""
        secondOperand = ""
        operator = null
        isOperatorSelected = false
    }
}
