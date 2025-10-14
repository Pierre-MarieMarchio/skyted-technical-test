package com.pierre_marie_marchio.skyted_technical_test.presentation.common.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val DarkColorScheme = darkColorScheme(
    background = Color(0xFF0D0D0D),
    surface = Color(0xFF161616),
    surfaceVariant = Color(0xFF1F1F1F),
    primary = Color(0xFFE50914),
    secondary = Color(0xFFB3B3B3),
    onBackground = Color.White,
    onSurface = Color.White,
    onPrimary = Color.White
)

val LightColorScheme = lightColorScheme(
    background = Color(0xFFF9F9F9),
    surface = Color.White,
    surfaceVariant = Color(0xFFF0F0F0),
    primary = Color(0xFFDB0000),
    secondary = Color(0xFF555555),
    onBackground = Color.Black,
    onSurface = Color.Black,
    onPrimary = Color.White
)