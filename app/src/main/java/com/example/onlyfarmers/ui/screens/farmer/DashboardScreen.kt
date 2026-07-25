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

// S7 — Farmer Dashboard · today's rate
// TODO Phase 3: dark bg, greeting + location, today's rate card (big price + sparkline),
//               camera CTA card, voice CTA, orders badge button,
//               3-tab bottom nav (Nyumbani / Oda / Pesa)
@Composable
fun DashboardScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoardInk),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S7 · Farmer Dashboard", color = Cream)
    }
}
