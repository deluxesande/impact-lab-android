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

// S8 — New Listing · camera → AI rate
// TODO Phase 3: camera viewfinder (top ~40%), AI overlay (produce type + grade + weight),
//               big AI rate display vs broker price, kg stepper (64dp targets),
//               "CHAPISHA · PUBLISH" CTA
@Composable
fun NewListingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoardInk),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S8 · New Listing", color = Cream)
    }
}
