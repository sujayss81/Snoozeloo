package org.sujay.snoozeloo.features.alarmlist.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.sujay.snoozeloo.core.utils.getAlarmInText
import org.sujay.snoozeloo.core.utils.getAmPm
import org.sujay.snoozeloo.core.utils.getFormattedTimeFromEpochMillis
import org.sujay.snoozeloo.data.AlarmUIModel

@Composable
fun ItemAlarmList(
    modifier: Modifier = Modifier,
    alarmUIModel: AlarmUIModel,
    onStateChangeAlarm: (Boolean) -> Unit
) {
    Card(
        modifier = modifier, shape = RoundedCornerShape(10.dp), colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier,
                    text = alarmUIModel.name ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.W600
                )

                val primaryColor = MaterialTheme.colorScheme.primary
                val secondaryColor = MaterialTheme.colorScheme.secondary

                Switch(
                    modifier = Modifier.offset(y = (-10).dp),
                    checked = alarmUIModel.isActive, onCheckedChange = {
                        onStateChangeAlarm(!alarmUIModel.isActive)
                    }, colors = SwitchDefaults.colors(
                        checkedTrackColor = secondaryColor,
                        uncheckedTrackColor = primaryColor,
                        uncheckedThumbColor = Color.White,
                        uncheckedBorderColor = Color.Transparent
                    ),
                    thumbContent = {
                        Canvas(modifier = Modifier) {
                            drawCircle(
                                color = Color.White,
                                radius = 14.dp.toPx()
                            )
                        }
                    })
            }

            val instant = Instant.fromEpochMilliseconds(alarmUIModel.timeInMillis)
            val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

            Row {
                Text(
                    modifier = Modifier.alignByBaseline(),
                    text = alarmUIModel.timeInMillis.getFormattedTimeFromEpochMillis(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 42.sp
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    modifier = Modifier.alignByBaseline(),
                    text = localDateTime.getAmPm(),
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            localDateTime.getAlarmInText()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}