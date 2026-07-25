package com.example.onlyfarmers.ui.screens.consumer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.ConsumerBg

// S5 — Basket · savings + swap
// TODO Phase 2: total-saved banner (dark), item rows with ±qty stepper,
//               AI swap suggestion card (dashed green border), order summary,
//               "Place order" CTA + M-Pesa / pay on delivery note
@Composable
fun CartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ConsumerBg),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S5 · Basket", color = BoardInk)
    }
}
