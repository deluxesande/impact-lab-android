package com.example.onlyfarmers.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ConsumerColorScheme = lightColorScheme(
    primary = BuyerGreen,
    onPrimary = Cream,
    primaryContainer = BuyerGreen.copy(alpha = 0.12f),
    secondary = FarmLime,
    onSecondary = BoardInk,
    background = ConsumerBg,
    surface = ConsumerBg,
    onBackground = BoardInk,
    onSurface = BoardInk,
    tertiary = SavingsOrange,
)

private val FarmerColorScheme = darkColorScheme(
    primary = FarmLime,
    onPrimary = BoardInk,
    primaryContainer = FarmLime.copy(alpha = 0.12f),
    secondary = BuyerGreen,
    onSecondary = Cream,
    background = BoardInk,
    surface = BoardInk,
    onBackground = Cream,
    onSurface = Cream,
    tertiary = SavingsOrange,
)

@Composable
fun FarmersTheme(
    farmerMode: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (farmerMode) FarmerColorScheme else ConsumerColorScheme,
        typography = FarmersTypography,
        content = content,
    )
}
