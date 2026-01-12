package com.example.calculator

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.system.exitProcess

@Composable
fun CalculatorApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(
                onSimpleClick = { navController.navigate("calculator/simple") },
                onAdvancedClick = { navController.navigate("calculator/advanced") },
                onAboutClick = { navController.navigate("about") },
                onExitClick = { exitProcess(0) }
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
        composable("about") {
            AboutScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}