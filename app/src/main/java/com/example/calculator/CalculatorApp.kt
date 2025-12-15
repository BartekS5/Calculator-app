package com.example.calculator

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun CalculatorApp() {
    val navController = rememberNavController()
    // ViewModel is scoped to the NavHost to survive navigation if needed,
    // or scoped to individual screens. Here we use a fresh VM for the calculator.

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(
                onSimpleClick = { navController.navigate("calculator/simple") },
                onAdvancedClick = { navController.navigate("calculator/advanced") },
                onExitClick = { System.exit(0) } // Requirement 2: Close App
            )
        }
        composable("calculator/{type}") { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "simple"
            val viewModel: CalculatorViewModel = viewModel()
            CalculatorScreen(
                isAdvanced = type == "advanced",
                state = viewModel.display,
                onAction = viewModel::onAction,
                onBack = { navController.popBackStack() }
            )
        }
    }
}