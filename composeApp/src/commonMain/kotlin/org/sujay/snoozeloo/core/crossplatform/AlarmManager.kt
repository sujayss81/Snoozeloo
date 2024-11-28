package org.sujay.snoozeloo.core.crossplatform

import org.sujay.snoozeloo.data.AlarmUIModel

expect class AlarmManager {

    fun saveAlarm(alarmUIModel: AlarmUIModel)
}