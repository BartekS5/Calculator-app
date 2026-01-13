package com.example.calculator

import androidx.compose.ui.graphics.Color

data class ButtonData(
    val symbol: String,
    val action: CalculatorAction,
    val color: Color = Color.Gray
)

val OpColor = Color(0xFFFF9800) // Orange
val NumColor = Color(0xFF616161) // Dark Gray
val FuncColor = Color(0xFF2196F3) // Blue (for advanced)

val simpleButtonList = listOf(
    ButtonData("AC", CalculatorAction.Clear, Color.Gray),
    ButtonData("+/-", CalculatorAction.Operation("*(-1)"), Color.Gray),
    ButtonData("bksp", CalculatorAction.Delete, Color.Gray),
    ButtonData("/", CalculatorAction.Operation("/"), OpColor),

    ButtonData("7", CalculatorAction.Number("7"), NumColor),
    ButtonData("8", CalculatorAction.Number("8"), NumColor),
    ButtonData("9", CalculatorAction.Number("9"), NumColor),
    ButtonData("*", CalculatorAction.Operation("*"), OpColor),

    ButtonData("4", CalculatorAction.Number("4"), NumColor),
    ButtonData("5", CalculatorAction.Number("5"), NumColor),
    ButtonData("6", CalculatorAction.Number("6"), NumColor),
    ButtonData("-", CalculatorAction.Operation("-"), OpColor),

    ButtonData("1", CalculatorAction.Number("1"), NumColor),
    ButtonData("2", CalculatorAction.Number("2"), NumColor),
    ButtonData("3", CalculatorAction.Number("3"), NumColor),
    ButtonData("+", CalculatorAction.Operation("+"), OpColor),

    ButtonData("0", CalculatorAction.Number("0"), NumColor),
    ButtonData(".", CalculatorAction.Number("."), NumColor),
    ButtonData("=", CalculatorAction.Calculate, OpColor)
)

val advancedButtonList = listOf(
    // Row 1
    ButtonData("AC", CalculatorAction.Clear, Color.Gray),
    ButtonData("bksp", CalculatorAction.Delete, Color.Gray),
    ButtonData("(", CalculatorAction.Number("("), Color.Gray),
    ButtonData(")", CalculatorAction.Number(")"), Color.Gray),

    // Row 2
    ButtonData("sin", CalculatorAction.MathFunction("sin"), FuncColor),
    ButtonData("cos", CalculatorAction.MathFunction("cos"), FuncColor),
    ButtonData("tan", CalculatorAction.MathFunction("tan"), FuncColor),
    ButtonData("ln", CalculatorAction.MathFunction("log"), FuncColor),

    // Row 3
    ButtonData("sqrt", CalculatorAction.MathFunction("sqrt"), FuncColor),
    ButtonData("log", CalculatorAction.MathFunction("log10"), FuncColor),
    ButtonData("+/-", CalculatorAction.Operation("*(-1)"), FuncColor),
    ButtonData("x^y", CalculatorAction.Operation("^"), FuncColor),

    //Row 4
    ButtonData("7", CalculatorAction.Number("7"), NumColor),
    ButtonData("8", CalculatorAction.Number("8"), NumColor),
    ButtonData("9", CalculatorAction.Number("9"), NumColor),
    ButtonData("/", CalculatorAction.Operation("/"), OpColor),

    // Row 3
    ButtonData("4", CalculatorAction.Number("4"), NumColor),
    ButtonData("5", CalculatorAction.Number("5"), NumColor),
    ButtonData("6", CalculatorAction.Number("6"), NumColor),
    ButtonData("*", CalculatorAction.Operation("*"), OpColor),

    // Row 4
    ButtonData("1", CalculatorAction.Number("1"), NumColor),
    ButtonData("2", CalculatorAction.Number("2"), NumColor),
    ButtonData("3", CalculatorAction.Number("3"), NumColor),
    ButtonData("-", CalculatorAction.Operation("-"), OpColor),

    // Row 5
    ButtonData("0", CalculatorAction.Number("0"), NumColor),
    ButtonData(".", CalculatorAction.Number("."), NumColor),
    ButtonData("=", CalculatorAction.Calculate, OpColor),
    ButtonData("+", CalculatorAction.Operation("+"), OpColor)
)