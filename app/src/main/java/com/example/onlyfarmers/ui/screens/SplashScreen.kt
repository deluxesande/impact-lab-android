package com.example.onlyfarmers.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Agriculture
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.components.LangPill
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

// S1 — Splash / Role picker
@Composable
fun SplashScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    val alpha = remember { Animatable(0f) }
    val translateY = remember { Animatable(32f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, tween(700, easing = FastOutSlowInEasing))
    }
    LaunchedEffect(Unit) {
        translateY.animateTo(0f, tween(700, easing = FastOutSlowInEasing))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoardInk)
            .systemBarsPadding(),
    ) {
        LangPill(
            selected = lang,
            onSelect = onLangChange,
            onDark = true,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .graphicsLayer {
                    this.alpha = alpha.value
                    this.translationY = translateY.value
                },
        ) {
            Spacer(Modifier.weight(1f))

            // Icon mark — solid FarmLime box per design
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(FarmLime),
            )

            Spacer(Modifier.height(26.dp))

            // Wordmark
            Text(
                text = "Farmers",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.02).em,
                    lineHeight = 0.95.em,
                ),
                color = Cream,
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = if (lang == "EN")
                    "Fair fixed rates from the shamba.\nGroceries below mall price."
                else
                    "Bei ya mkulima moja kwa moja.\nNunua chini ya bei ya duka.",
                style = MaterialTheme.typography.bodyLarge,
                color = Cream.copy(alpha = 0.66f),
                lineHeight = 1.45.em,
            )

            Spacer(Modifier.weight(1f))

            // Role cards
            RoleCard(
                icon = Icons.Rounded.Agriculture,
                title = if (lang == "EN") "I'm a Farmer" else "Mimi ni Mkulima",
                subtitle = if (lang == "EN") "Mkulima · sell at a fixed rate" else "Uza mazao kwa bei ya kawaida",
                filled = true,
                onClick = { navController.navigate(Screen.Auth.createRoute("farmer")) },
            )

            Spacer(Modifier.height(12.dp))

            RoleCard(
                icon = Icons.Rounded.ShoppingBag,
                title = if (lang == "EN") "I'm a Buyer" else "Mimi ni Mnunuzi",
                subtitle = if (lang == "EN") "Mnunuzi · shop below mall price" else "Nunua kwa bei ya shamba",
                filled = false,
                onClick = { navController.navigate(Screen.Auth.createRoute("buyer")) },
            )

            Spacer(Modifier.height(18.dp))

            Text(
                text = "Sheng? Kiswahili? Sema — tunaelewa.",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.5.sp),
                color = Cream.copy(alpha = 0.45f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(30.dp))
        }
    }
}

@Composable
private fun RoleCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    filled: Boolean,
    onClick: () -> Unit,
) {
    val bg = if (filled) FarmLime else Color.Transparent
    val titleColor = if (filled) BoardInk else Cream
    val subtitleColor = if (filled) BoardInk.copy(alpha = 0.6f) else TextOnDarkMuted
    val borderModifier = if (filled) Modifier
    else Modifier.border(1.dp, Cream.copy(alpha = 0.18f), RoundedCornerShape(20.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(bg)
            .then(borderModifier)
            .clickable(onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (filled) BoardInk.copy(alpha = 0.14f)
                    else Cream.copy(alpha = 0.14f)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (filled) BoardInk else Cream,
                modifier = Modifier.size(24.dp),
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 1.1.em,
                ),
                color = titleColor,
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelMedium,
                color = subtitleColor,
            )
        }

        Icon(
            imageVector = Icons.Rounded.ChevronRight,
            contentDescription = null,
            tint = if (filled) BoardInk.copy(alpha = 0.5f) else Cream.copy(alpha = 0.7f),
            modifier = Modifier.size(20.dp),
        )
    }
}
