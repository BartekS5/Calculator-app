package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.*

class CalculatorViewModel : ViewModel() {

    // State variables
    var display by mutableStateOf("0")
        private set

    private var firstOperand: Double? = null
    private var pendingOperation: String? = null
    private var isNewEntry: Boolean = true

    private var lastActionWasClear: Boolean = false

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> calculateResult()
            is CalculatorAction.Clear -> performClear()
            is CalculatorAction.Delete -> backspace()
            is CalculatorAction.SignChange -> toggleSign()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.MathFunction -> applyFunction(action.function)
        }
    }

    private fun enterNumber(number: Int) {
        lastActionWasClear = false
        if (isNewEntry || display == "0" || display == "Error") {
            display = number.toString()
            isNewEntry = false
        } else {
            display += number
        }
    }

    private fun enterOperation(op: String) {
        lastActionWasClear = false
        if (firstOperand == null) {
            firstOperand = display.toDoubleOrNull()
        } else if (!isNewEntry) {
            // Chain calculation if user hits + then * without =
            calculateResult()
        }
        pendingOperation = op
        isNewEntry = true
    }

    private fun applyFunction(func: String) {
        val value = display.toDoubleOrNull() ?: return
        var result = 0.0

        // Error handling for domains (Requirement 5)
        try {
            result = when (func) {
                "sin" -> sin(value)
                "cos" -> cos(value)
                "tan" -> tan(value)
                "ln" -> if (value > 0) ln(value) else Double.NaN
                "log" -> if (value > 0) log10(value) else Double.NaN
                "sqrt" -> if (value >= 0) sqrt(value) else Double.NaN
                "%" -> value / 100.0
                "x^2" -> value.pow(2)
                else -> value
            }
            display = formatResult(result)
            isNewEntry = true
        } catch (e: Exception) {
            display = "Error"
        }
    }

    private fun calculateResult() {
        val secondOperand = display.toDoubleOrNull()
        val op = pendingOperation
        val first = firstOperand

        if (first != null && secondOperand != null && op != null) {
            var result = 0.0
            // Requirement 3 & 4 Math
            when (op) {
                "+" -> result = first + secondOperand
                "-" -> result = first - secondOperand
                "*" -> result = first * secondOperand
                "/" -> result = if (secondOperand != 0.0) first / secondOperand else Double.NaN
                "x^y" -> result = first.pow(secondOperand)
            }

            display = formatResult(result)
            firstOperand = result // Chain calculations
            pendingOperation = null
            isNewEntry = true
        }
    }

    private fun performClear() {
        // Requirement 3: First press clears display (CE), Double press (AC)
        if (lastActionWasClear) {
            // AC: Clear Everything
            firstOperand = null
            pendingOperation = null
            display = "0"
            isNewEntry = true
        } else {
            // CE: Clear Entry
            display = "0"
            isNewEntry = true
            lastActionWasClear = true
        }
    }

    private fun toggleSign() {
        if (display != "0" && display != "Error") {
            val value = display.toDoubleOrNull() ?: 0.0
            display = formatResult(value * -1)
        }
    }

    private fun formatResult(value: Double): String {
        if (value.isNaN() || value.isInfinite()) return "Error"
        // Remove trailing .0 for integers
        return if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    private fun backspace() {
        if (display == "Error" || isNewEntry) {
            display = "0"
            isNewEntry = true
            return
        }

        display = if (display.length > 1) {
            display.dropLast(1)
        } else {
            "0"
        }

        // Handle case where only "-" remains or string became empty
        if (display == "-" || display.isEmpty()) {
            display = "0"
            isNewEntry = true
        }
    }

    private fun enterDecimal() {
        if (isNewEntry || display == "Error") {
            display = "0."
            isNewEntry = false
        } else if (!display.contains(".")) {
            display += "."
        }
    }
}

// Sealed class to define actions
sealed class CalculatorAction {
    data class Number(val number: Int) : CalculatorAction()
    data class Operation(val operation: String) : CalculatorAction()
    data class MathFunction(val function: String) : CalculatorAction()
    object Calculate : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object SignChange : CalculatorAction()
    object Decimal : CalculatorAction()
}