package org.sujay.snoozeloo

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sujay.snoozeloo.core.theme.SnoozelooTheme
import org.sujay.snoozeloo.data.AlarmUIModel
import org.sujay.snoozeloo.features.alarmtrigger.AlarmTriggerScreen

@Composable
@Preview
fun App() {
    SnoozelooTheme {
        AlarmTriggerScreen(
            AlarmUIModel(
                name = "Work",
                timeInMillis = 1732735800000,
                isActive = true
            )
        )
    }
}