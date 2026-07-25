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

// S4 — AI Basket / Chat + voice
// TODO Phase 2: chat bubbles (user right / AI left), AI cart response card
//               (items + savings total), suggestion chips, text + mic input bar
@Composable
fun AiChatScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ConsumerBg),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S4 · AI Chat", color = BoardInk)
    }
}
