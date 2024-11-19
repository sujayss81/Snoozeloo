package org.sujay.snoozeloo.core.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun SnoozelooTheme(content: @Composable () -> Unit) {

    val colorScheme = lightColors(
        primary = primary,
        onPrimary = onPrimary,
        secondary = secondary,
        background = background
    )

    MaterialTheme(
        colors = colorScheme,
        typography = MonteserratTypography(),
        content = content
    )
}