package com.example.onlyfarmers.ui.screens.farmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream

// S10 — Pesa · earnings + AI forecast
// TODO Phase 3: month total + % vs last month, weekly bar chart (last bar = forecast dashed),
//               AI forecast cards (oversupply warning + demand signal),
//               M-Pesa withdraw row
@Composable
fun EarningsScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoardInk),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S10 · Earnings / Pesa", color = Cream)
    }
}
