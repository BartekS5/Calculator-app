package com.example.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MenuScreen(onSimpleClick: () -> Unit, onAdvancedClick: () -> Unit, onExitClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("My Calculator App", style = MaterialTheme.typography.headlineMedium)
        Text("Author: Bartlomiej Seczkowski 250270", style = MaterialTheme.typography.bodyLarge)
        Text("A simple and advanced calculator for Android.", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onSimpleClick, modifier = Modifier.fillMaxWidth(0.6f)) {
            Text("Simple Calculator")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onAdvancedClick, modifier = Modifier.fillMaxWidth(0.6f)) {
            Text("Advanced Calculator")
        }
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(onClick = onExitClick, modifier = Modifier.fillMaxWidth(0.6f)) {
            Text("Exit")
        }
    }
}