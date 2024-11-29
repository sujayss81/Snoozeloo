package org.sujay.snoozeloo.core.crossplatform

import org.sujay.snoozeloo.data.AlarmUIModel

expect class AlarmManager() {

    suspend fun saveAlarm(alarmUIModel: AlarmUIModel)

    suspend fun getAlarms(): List<AlarmUIModel>

    suspend fun setAlarmState(alarmUIModel: AlarmUIModel)
}