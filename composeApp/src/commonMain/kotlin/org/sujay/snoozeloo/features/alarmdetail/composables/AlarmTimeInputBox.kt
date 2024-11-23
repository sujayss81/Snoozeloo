package org.sujay.snoozeloo.features.alarmdetail.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.vectorResource
import org.sujay.snoozeloo.core.theme.inactiveColor
import org.sujay.snoozeloo.core.utils.get0AppendedTime
import snoozeloo.composeapp.generated.resources.Res
import snoozeloo.composeapp.generated.resources.ic_colon

@Composable
fun AlarmTimeInputBox(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(Color.White).padding(24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TimeBox(time = 10, isInactive = false)
            Spacer(Modifier.width(10.dp))
            Image(vectorResource(Res.drawable.ic_colon), contentDescription = null)
            Spacer(Modifier.width(10.dp))
            TimeBox(time = 0, isInactive = true)
        }

        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp).align(Alignment.CenterHorizontally),
            text = "Alarm in 7h 15min",
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun TimeBox(modifier: Modifier = Modifier, time: Int, isInactive: Boolean) {
    Box(
        modifier.clip(RoundedCornerShape(10.dp)).background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = time.get0AppendedTime(),
            style = MaterialTheme.typography.h1,
            fontSize = 52.sp,
            color = if (!isInactive) MaterialTheme.colors.secondary else inactiveColor
        )
    }
}