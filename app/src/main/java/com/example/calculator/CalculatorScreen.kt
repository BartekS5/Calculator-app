package com.example.calculator

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(
    isAdvanced: Boolean,
    state: String,
    onAction: (CalculatorAction) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    LaunchedEffect(state) {
        scrollState.animateScrollTo(scrollState.maxValue)
        if (state == "Error") {
            Toast.makeText(context, "Invalid Operation", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
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
                fontSize = 45.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                softWrap = false,
                modifier = Modifier.horizontalScroll(scrollState)
            )

            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back",
                    tint = Color.Unspecified
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val buttons = remember(isAdvanced) {
                if (isAdvanced) advancedButtonList else simpleButtonList
            }
            val colCount = 4

            val rows = buttons.chunked(colCount)

            rows.forEach { rowButtons ->
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowButtons.forEach { btn ->
                        val weight = if ((!isAdvanced && btn.symbol == "0")) 2f else 1f

                        CalculatorButton(
                            symbol = btn.symbol,
                            modifier = Modifier
                                .weight(weight)
                                .fillMaxHeight(),
                            onClick = { onAction(btn.action) },
                            color = btn.color
                        )
                    }
                }
            }
        }
    }
}

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