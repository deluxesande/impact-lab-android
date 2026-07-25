package com.example.onlyfarmers.ui.screens

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

// S2 — Sign in / Sign up
// Auth: email + password fields, "Sign in" CTA, Google sign-in button
// TODO Phase 1: role badge pill, email input, password input, Google button, T&C
@Composable
fun AuthScreen(navController: NavController, role: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ConsumerBg),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "S2 · Auth — role: $role", color = BoardInk)
    }
}
