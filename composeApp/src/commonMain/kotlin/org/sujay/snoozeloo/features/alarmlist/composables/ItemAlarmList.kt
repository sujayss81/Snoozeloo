package org.sujay.snoozeloo.features.alarmlist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.sujay.snoozeloo.core.utils.formattedAlarmTime
import org.sujay.snoozeloo.core.utils.getAlarmInText
import org.sujay.snoozeloo.core.utils.getAmPm
import org.sujay.snoozeloo.data.AlarmUIModel

@Composable
fun ItemAlarmList(modifier: Modifier = Modifier, alarmUIModel: AlarmUIModel, onStateChangeAlarm: (Boolean) -> Unit) {
    Card(modifier = modifier, shape = RoundedCornerShape(10.dp)) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = alarmUIModel.name ?: "",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.W600
                )

                Switch(checked = alarmUIModel.isActive, onCheckedChange = {
                    onStateChangeAlarm(!alarmUIModel.isActive)
                })
            }

            val instant = Instant.fromEpochMilliseconds(alarmUIModel.timeInMillis)
            val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

            Row {
                Text(
                    modifier = Modifier.alignByBaseline(),
                    text = localDateTime.formattedAlarmTime(),
                    style = MaterialTheme.typography.h1,
                    fontSize = 42.sp
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    modifier = Modifier.alignByBaseline(),
                    text = localDateTime.getAmPm(),
                    style = MaterialTheme.typography.h1
                )
            }

            localDateTime.getAlarmInText()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}