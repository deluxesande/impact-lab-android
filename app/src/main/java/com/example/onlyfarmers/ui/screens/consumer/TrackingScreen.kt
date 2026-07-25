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

// S6 — Confirmed · order tracking
// TODO Phase 2: map placeholder (top half), bottom sheet (pull handle),
//               ETA "24 min", 4-step progress bar, timeline steps,
//               address card, AI swap note, rider card with call button
@Composable
fun TrackingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ConsumerBg),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S6 · Order Tracking", color = BoardInk)
    }
}
