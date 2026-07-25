package com.example.onlyfarmers.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

// S1 — Splash / Role picker
// TODO Phase 1: "I'm a Farmer" (lime card) + "I'm a Buyer" (ghost card), EN/SW pill
@Composable
fun SplashScreen(navController: NavController) {
    val alpha = remember { Animatable(0f) }
    val translateY = remember { Animatable(24f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, tween(600, easing = FastOutSlowInEasing))
    }
    LaunchedEffect(Unit) {
        translateY.animateTo(0f, tween(600, easing = FastOutSlowInEasing))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoardInk),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.graphicsLayer {
                this.alpha = alpha.value
                this.translationY = translateY.value
            },
        ) {
            // Icon mark — leaf in a subtle lime-tinted box
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(FarmLime.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Eco,
                    contentDescription = null,
                    tint = FarmLime,
                    modifier = Modifier.size(36.dp),
                )
            }

            Spacer(Modifier.height(28.dp))

            // Wordmark — "only" small muted + "farmers" large lime, baseline-aligned
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "only",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.06.em,
                    ),
                    color = TextOnDarkMuted,
                    modifier = Modifier.padding(bottom = 5.dp),
                )
                Text(
                    text = "farmers",
                    style = MaterialTheme.typography.displayMedium,
                    color = FarmLime,
                )
            }

            Spacer(Modifier.height(10.dp))

            // Swahili tagline
            Text(
                text = "duka la shambani",
                style = MaterialTheme.typography.labelMedium,
                color = TextOnDarkMuted,
                letterSpacing = 0.12.em,
            )
        }
    }
}
