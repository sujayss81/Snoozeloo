package org.sujay.snoozeloo.core.crossplatform

import org.sujay.snoozeloo.data.AlarmUIModel

expect class SNAlarmManager() {

    suspend fun saveAlarm(alarmUIModel: AlarmUIModel)

    suspend fun getAlarms(): List<AlarmUIModel>

    suspend fun setAlarmState(alarmUIModel: AlarmUIModel)

    fun setAlarmTrigger(alarmUIModel: AlarmUIModel, trigger: Boolean)
}