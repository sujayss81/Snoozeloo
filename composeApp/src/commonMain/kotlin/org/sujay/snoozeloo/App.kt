package org.sujay.snoozeloo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sujay.snoozeloo.core.theme.SnoozelooTheme
import org.sujay.snoozeloo.core.utils.NavConstants
import org.sujay.snoozeloo.data.AlarmUIModel
import org.sujay.snoozeloo.features.alarmdetail.AlarmDetailScreen
import org.sujay.snoozeloo.features.alarmlist.AlarmListScreen
import org.sujay.snoozeloo.features.alarmtrigger.AlarmTriggerScreen

@Composable
@Preview
fun App() {
    SnoozelooTheme {
        val navController = rememberNavController()

        val alarmList = remember {
            mutableStateListOf<AlarmUIModel>()
        }

        NavHost(
            navController = navController,
            startDestination = NavConstants.ALARM_LIST_SCREEN.name
        ) {
            composable(route = NavConstants.ALARM_LIST_SCREEN.name) {
                AlarmListScreen(alarmList, onFabClick = {
                    navController.navigate(NavConstants.ALARM_DETAILS_SCREEN.name)
                }, onStateChange = { alarm, state ->
                    val index = alarmList.indexOf(alarm)
                    alarmList[index] = alarmList[index].copy(isActive = state)
                })
            }

            composable(route = NavConstants.ALARM_DETAILS_SCREEN.name) {
                AlarmDetailScreen {
                    alarmList.add(it)
                    navController.navigateUp()
                }
            }

            composable(route = NavConstants.ALARM_TRIGGER_SCREEN.name) {
                val alarmUIModel = AlarmUIModel(
                    name = "Work",
                    isActive = true,
                    timeInMillis = 0L
                )

                AlarmTriggerScreen(navController, alarmUIModel)
            }
        }
    }
}