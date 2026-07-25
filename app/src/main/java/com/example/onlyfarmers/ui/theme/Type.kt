package com.example.onlyfarmers.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// TODO: Replace with downloaded Archivo + IBM Plex Mono font files placed in res/font/
// Design calls for: Archivo (400–800) for UI, IBM Plex Mono (400–600) for prices/labels
val Archivo = FontFamily.Default
val IbmPlexMono = FontFamily.Monospace

val FarmersTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 52.sp,
        lineHeight = 52.sp,
        letterSpacing = (-0.03).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 44.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.02).sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.02).sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = IbmPlexMono,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = IbmPlexMono,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.08.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = IbmPlexMono,
        fontWeight = FontWeight.Normal,
        fontSize = 9.5.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.14.sp,
    ),
)
