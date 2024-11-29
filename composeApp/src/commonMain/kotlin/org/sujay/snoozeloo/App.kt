package org.sujay.snoozeloo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sujay.snoozeloo.core.crossplatform.SNAlarmManager
import org.sujay.snoozeloo.core.theme.SnoozelooTheme
import org.sujay.snoozeloo.core.utils.NavConstants
import org.sujay.snoozeloo.data.AlarmUIModel
import org.sujay.snoozeloo.features.alarmdetail.AlarmDetailScreen
import org.sujay.snoozeloo.features.alarmlist.AlarmListScreen
import org.sujay.snoozeloo.features.alarmtrigger.AlarmTriggerScreen

@Composable
@Preview
fun App(isTriggerScreen: Boolean = false, alarmName: String, alarmTime: Long) {
    SnoozelooTheme {
        val navController = rememberNavController()
        val sNAlarmManager = SNAlarmManager()

        var alarmList = remember {
            mutableStateListOf<AlarmUIModel>()
        }

        LaunchedEffect(true) {
            sNAlarmManager.getAlarms().let {
                alarmList.addAll(it)
            }
        }

        val coroutineScope = rememberCoroutineScope()

        NavHost(
            navController = navController,
            startDestination = if (isTriggerScreen) NavConstants.ALARM_TRIGGER_SCREEN.name else NavConstants.ALARM_LIST_SCREEN.name
        ) {
            composable(route = NavConstants.ALARM_LIST_SCREEN.name) {
                AlarmListScreen(alarmList, onFabClick = {
                    navController.navigate(NavConstants.ALARM_DETAILS_SCREEN.name)
                }, onStateChange = { alarm, state ->
                    val index = alarmList.indexOf(alarm)
                    alarmList[index] = alarmList[index].copy(isActive = state)
                    sNAlarmManager.setAlarmTrigger(alarm, state)

                    coroutineScope.launch {
                        sNAlarmManager.setAlarmState(alarm.copy(isActive = state))
                    }
                })
            }

            composable(route = NavConstants.ALARM_DETAILS_SCREEN.name) {
                AlarmDetailScreen {
                    alarmList.add(it)
                    sNAlarmManager.setAlarmTrigger(it, true)
                    navController.navigateUp()

                    coroutineScope.launch {
                        sNAlarmManager.saveAlarm(it)
                    }
                }
            }

            composable(route = NavConstants.ALARM_TRIGGER_SCREEN.name) {
                val alarmUIModel = AlarmUIModel(
                    name = alarmName, isActive = true, timeInMillis = alarmTime
                )

                AlarmTriggerScreen(alarmUIModel) {
                    navController.navigateUp()
                }
            }
        }
    }
}