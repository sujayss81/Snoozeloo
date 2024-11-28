package org.sujay.snoozeloo.features.alarmtrigger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.sujay.snoozeloo.core.theme.grey
import org.sujay.snoozeloo.core.utils.LargeAlarmIcon
import org.sujay.snoozeloo.core.utils.getFormattedTimeFromEpochMillis
import org.sujay.snoozeloo.data.AlarmUIModel

@Composable
fun AlarmTriggerScreen(navController: NavController, alarmUIModel: AlarmUIModel) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LargeAlarmIcon()

            Text(
                text = alarmUIModel.timeInMillis?.getFormattedTimeFromEpochMillis() ?: "",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = alarmUIModel.name?.uppercase() ?: "",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                ),
            )

            Button(
                modifier = Modifier.width(200.dp).padding(vertical = 8.dp),
                onClick = {
                    // TODO handle alarm dismiss
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = grey,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.W600,
                    text = "Turn Off"
                )
            }
        }
    }
}