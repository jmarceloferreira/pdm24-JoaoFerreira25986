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
        val currentOperand = if (!isOperatorSelected) firstOperand else secondOperand

        if (input == "." && currentOperand.contains(".")) return

        if (currentOperand == "0" && input != ".") {
            if (!isOperatorSelected) firstOperand = input else secondOperand = input
        } else {
            if (!isOperatorSelected) firstOperand += input else secondOperand += input
        }
        updateDisplay()
    }

    private fun updateDisplay() {
        displayText = if (!isOperatorSelected) firstOperand else secondOperand
        if (displayText.isEmpty()) displayText = "0"
    }

    private fun handleOperatorInput(input: String) {
        if (firstOperand.isNotEmpty()) {
            operator = input
            isOperatorSelected = true
        }
    }

    private fun calculateResult() {
        if (secondOperand.isEmpty()) return

        val result = try {
            when (operator) {
                "+" -> firstOperand.toDouble() + secondOperand.toDouble()
                "-" -> firstOperand.toDouble() - secondOperand.toDouble()
                "x" -> firstOperand.toDouble() * secondOperand.toDouble()
                "÷" -> if (secondOperand.toDouble() != 0.0) {
                    firstOperand.toDouble() / secondOperand.toDouble()
                } else {
                    throw ArithmeticException("Divisão por zero")
                }
                else -> null
            }
        } catch (e: Exception) {
            null
        }

        displayText = result?.let {
            if (it % 1 == 0.0) it.toInt().toString() else it.toString()
        } ?: "Error"

        resetAfterCalculation(displayText)
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
