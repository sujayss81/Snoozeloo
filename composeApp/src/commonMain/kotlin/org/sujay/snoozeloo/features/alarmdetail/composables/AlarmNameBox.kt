package org.sujay.snoozeloo.features.alarmdetail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AlarmNameBox(modifier: Modifier = Modifier, alarmName: String) {
    Row(
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(Color.White).padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Alarm Name",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.W600
        )

        Text(
            text = alarmName,
            style = MaterialTheme.typography.bodySmall
        )
    }
}