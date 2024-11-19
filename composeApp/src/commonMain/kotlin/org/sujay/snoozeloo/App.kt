package org.sujay.snoozeloo

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sujay.snoozeloo.core.theme.SnoozelooTheme
import org.sujay.snoozeloo.features.alarmlist.AlarmListScreen

@Composable
@Preview
fun App() {
    SnoozelooTheme {
        AlarmListScreen()
    }
}