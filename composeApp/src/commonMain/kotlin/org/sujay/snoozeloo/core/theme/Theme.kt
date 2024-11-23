package org.sujay.snoozeloo.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SnoozelooTheme(content: @Composable () -> Unit) {

    val colorScheme = lightColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        secondary = secondary,
        background = Color(0xFFF6F6F6)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MonteserratTypography(),
        content = content
    )
}