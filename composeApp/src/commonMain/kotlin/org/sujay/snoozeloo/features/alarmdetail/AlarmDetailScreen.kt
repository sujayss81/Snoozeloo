package org.sujay.snoozeloo.features.alarmdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.sujay.snoozeloo.core.theme.grey
import org.sujay.snoozeloo.features.alarmdetail.composables.AlarmNameBox
import org.sujay.snoozeloo.features.alarmdetail.composables.AlarmTimeInputBox
import snoozeloo.composeapp.generated.resources.Res
import snoozeloo.composeapp.generated.resources.ic_close
import snoozeloo.composeapp.generated.resources.save_button_text

@Composable
fun AlarmDetailScreen() {
    val isSaveButtonEnabled = true

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = vectorResource(Res.drawable.ic_close),
                    contentDescription = null
                )
                Button(
                    enabled = isSaveButtonEnabled,
                    onClick = {
                        // TOOD handle save function
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = grey,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.W600,
                        text = stringResource(Res.string.save_button_text)
                    )
                }
            }
        }) { contentPadding ->

        Column(
            modifier = Modifier.fillMaxWidth().padding(contentPadding).padding(top = 24.dp)
                .padding(horizontal = 16.dp)
        ) {
            AlarmTimeInputBox()
            Spacer(modifier = Modifier.height(16.dp))
            AlarmNameBox(alarmName = "Work")
        }

    }
}