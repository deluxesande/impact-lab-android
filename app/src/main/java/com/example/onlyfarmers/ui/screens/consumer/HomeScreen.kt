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

// S3 — Consumer Home / Browse
// TODO Phase 2: location header, search bar, AI basket banner + mic,
//               category chips, 2-col produce grid (photo + price + ~~mall price~~),
//               5-tab bottom nav (Home / Search / Basket / Orders / Account)
@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ConsumerBg),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S3 · Consumer Home", color = BoardInk)
    }
}
