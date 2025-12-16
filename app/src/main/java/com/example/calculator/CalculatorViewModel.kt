package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel : ViewModel() {

    var display by mutableStateOf("")
        private set

    fun onAction(action: CalculatorAction) {
        if (display == "Error") {
            display = ""
        }

        when (action) {
            is CalculatorAction.Number -> display += action.number
            is CalculatorAction.Operation -> display += action.operation
            is CalculatorAction.MathFunction -> display += "${action.function}("
            is CalculatorAction.Calculate -> calculateResult()
            is CalculatorAction.Clear -> display = ""
            is CalculatorAction.Delete -> {
                if (display.isNotEmpty()) {
                    display = display.dropLast(1)
                }
            }
            else -> {}
        }
    }

    private fun calculateResult() {
        if (display.isEmpty()) return

        try {
            val expression = ExpressionBuilder(display).build()

            val result = expression.evaluate()

            display = formatResult(result)

        } catch (e: Exception) {
            display = "Error"
            e.printStackTrace()
        }
    }

    private fun formatResult(value: Double): String {
        if (value.isNaN() || value.isInfinite()) return "Error"
        return if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }
}

sealed class CalculatorAction {
    data class Number(val number: String) : CalculatorAction()
    data class Operation(val operation: String) : CalculatorAction()
    data class MathFunction(val function: String) : CalculatorAction()
    object Calculate : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()

    // Kept for backward compatibility if needed, but unused in new logic
    object SignChange : CalculatorAction()
    object Decimal : CalculatorAction()
}