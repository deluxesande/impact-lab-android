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

// S9 — Orders · one decision at a time
// TODO Phase 3: stacked card UI (shadow-offset stack illusion), top card shows
//               buyer name + produce + qty + payout + boda time,
//               POKEA/ACCEPT + Kataa/REJECT buttons, voice affordance,
//               completed orders list below the card
@Composable
fun OrdersScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoardInk),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S9 · Orders", color = Cream)
    }
}
