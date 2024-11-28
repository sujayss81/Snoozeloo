package org.sujay.snoozeloo.features.alarmlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.sujay.snoozeloo.core.utils.LargeAlarmIcon
import org.sujay.snoozeloo.data.AlarmUIModel
import org.sujay.snoozeloo.features.alarmlist.composables.ItemAlarmList
import snoozeloo.composeapp.generated.resources.Res
import snoozeloo.composeapp.generated.resources.ic_add
import snoozeloo.composeapp.generated.resources.no_alarms_text
import snoozeloo.composeapp.generated.resources.your_alarms_title

@Composable
fun AlarmListScreen(alarmList: List<AlarmUIModel>, onFabClick: () -> Unit, onStateChange: (AlarmUIModel, Boolean) -> Unit) {

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
                text = stringResource(Res.string.your_alarms_title),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(60.dp),
                containerColor = MaterialTheme.colorScheme.secondary,
                onClick = {
                    onFabClick()
                },
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                shape = RoundedCornerShape(50.dp)
            ) {
                Image(
                    imageVector = vectorResource(Res.drawable.ic_add),
                    contentDescription = null,
                    alignment = Alignment.Center
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { contentPadding ->

        alarmList.isEmpty().let {
            if (it) {
                // No Alarms Layout
                Box(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        LargeAlarmIcon()

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            stringResource(Res.string.no_alarms_text),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // Alarm List
        LazyColumn(
            modifier = Modifier.fillMaxHeight().padding(contentPadding).padding(top = 24.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(alarmList) { index, alarm ->
                ItemAlarmList(
                    alarmUIModel = alarm,
                    onStateChangeAlarm = {
                        val selectedAlarm = alarmList[index]
                        onStateChange(selectedAlarm, it)
                    }
                )
            }
        }
    }
}
