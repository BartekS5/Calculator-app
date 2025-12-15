package com.example.calculator

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items // <--- CRITICAL IMPORT FIX
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- 1. Screen Composable ---
@Composable
fun CalculatorScreen(
    isAdvanced: Boolean,
    state: String,
    onAction: (CalculatorAction) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    // Requirement 5: Check for errors to show Toast
    LaunchedEffect(state) {
        if (state == "Error") {
            Toast.makeText(context, "Invalid Operation", Toast.LENGTH_SHORT).show()
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        // Display Area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = state,
                color = Color.White,
                fontSize = 60.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Keypad Area
        val buttons = remember(isAdvanced) {
            if (isAdvanced) advancedButtonList else simpleButtonList
        }

        // Responsive Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(if (isAdvanced) 5 else 4),
            modifier = Modifier.weight(2f),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // FIX: Ensure 'items' is imported from androidx.compose.foundation.lazy.grid.items
            items(buttons) { btn ->
                CalculatorButton(
                    symbol = btn.symbol,
                    modifier = Modifier.aspectRatio(if(isAdvanced) 1.5f else 1f),
                    onClick = { onAction(btn.action) },
                    color = btn.color
                )
            }
        }
    }
}

// --- 2. Button Component ---
@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier,
    onClick: () -> Unit,
    color: Color
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(color)
            .clickable { onClick() }
    ) {
        Text(text = symbol, fontSize = 24.sp, color = Color.White)
    }
}