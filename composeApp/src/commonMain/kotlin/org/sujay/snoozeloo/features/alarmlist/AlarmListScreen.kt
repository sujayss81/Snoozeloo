package org.sujay.snoozeloo.features.alarmlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import snoozeloo.composeapp.generated.resources.Res
import snoozeloo.composeapp.generated.resources.ic_add
import snoozeloo.composeapp.generated.resources.ic_alarm
import snoozeloo.composeapp.generated.resources.no_alarms_text
import snoozeloo.composeapp.generated.resources.your_alarms_title

@Composable
fun AlarmListScreen() {
    Scaffold(
        topBar = {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
                text = stringResource(Res.string.your_alarms_title),
                style = MaterialTheme.typography.h1
            )
        },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.size(60.dp), onClick = {
                // TODO navigate to Alarm Detail Screen
            }, elevation = FloatingActionButtonDefaults.elevation(0.dp)) {
                Image(
                    imageVector = vectorResource(Res.drawable.ic_add),
                    contentDescription = null,
                    alignment = Alignment.Center
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { contentPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    modifier = Modifier.size(62.dp),
                    imageVector = vectorResource(Res.drawable.ic_alarm),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    stringResource(Res.string.no_alarms_text),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
