package org.sujay.snoozeloo.features.alarmdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.vectorResource
import org.sujay.snoozeloo.core.utils.SaveButton
import org.sujay.snoozeloo.core.utils.getEpochMillisFromTime
import org.sujay.snoozeloo.data.AlarmUIModel
import org.sujay.snoozeloo.features.alarmdetail.composables.AlarmNameBox
import org.sujay.snoozeloo.features.alarmdetail.composables.AlarmTimeInputBox
import snoozeloo.composeapp.generated.resources.Res
import snoozeloo.composeapp.generated.resources.ic_close
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmDetailScreen() {

    var hourSelected: Int? by remember {
        mutableStateOf(0)
    }

    var minSelected: Int? by remember {
        mutableStateOf(0)
    }

    var alarmUIModel by remember {
        mutableStateOf(AlarmUIModel(isActive = false, timeInMillis = 0L, name = null))
    }

    var alarmTimeInMillis by remember {
        mutableStateOf(0L)
    }

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    var alarmName by remember {
        mutableStateOf("")
    }

    var showAlarmNameDialog by remember {
        mutableStateOf(false)
    }

    val isSaveButtonEnabled by remember {
        derivedStateOf {
            alarmTimeInMillis != 0L
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
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

                SaveButton(isSaveButtonEnabled = isSaveButtonEnabled) {

                    // Save Alarm
                    alarmUIModel = alarmUIModel.copy(
                        timeInMillis = hourSelected?.getEpochMillisFromTime(hourSelected, minSelected) ?: 0L,
                        name = alarmName
                    )

                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Alarm set",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }) { contentPadding ->

        Box(modifier = Modifier.fillMaxSize().padding(contentPadding)) {

            // Dialog

            if(showAlarmNameDialog) {

                AlertDialog(
                    title = {
                        Text("Alarm Name", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W600
                        ))
                    },
                    onDismissRequest = {
                        showAlarmNameDialog = true
                    },
                    confirmButton = {
                        SaveButton {
                            keyboardController?.hide()
                            showAlarmNameDialog = false
                        }
                    },
                    text = {
                        OutlinedTextField(
                            value = alarmName,
                            onValueChange = {
                                alarmName = it
                            }
                        )
                    }
                )
            }

            // Content

            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            ) {

                AlarmTimeInputBox(hour = hourSelected, min = minSelected, onHourChange = {
                    hourSelected = it
                    alarmTimeInMillis = hourSelected?.hours?.inWholeMilliseconds?.plus(
                        hourSelected?.minutes?.inWholeMilliseconds ?: 0L
                    ) ?: 0L
                }, onMinChange = {
                    minSelected = it
                    alarmTimeInMillis = hourSelected?.hours?.inWholeMilliseconds?.plus(
                        hourSelected?.minutes?.inWholeMilliseconds ?: 0L
                    ) ?: 0L
                })

                Spacer(modifier = Modifier.height(16.dp))

                AlarmNameBox(modifier = Modifier.clickable {
                    showAlarmNameDialog = true
                }, alarmName = alarmName)
            }
        }

    }
}
