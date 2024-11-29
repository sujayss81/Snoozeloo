package org.sujay.snoozeloo.core.crossplatform

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import io.realm.kotlin.ext.query
import org.sujay.snoozeloo.core.broadcastreceiver.AlarmReceiver
import org.sujay.snoozeloo.core.constant.AppConstants.ALARM_NAME
import org.sujay.snoozeloo.core.constant.AppConstants.ALARM_TIME
import org.sujay.snoozeloo.core.service.ContextProvider
import org.sujay.snoozeloo.core.service.RealmDBService
import org.sujay.snoozeloo.data.AlarmUIModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

actual class SNAlarmManager {
    @OptIn(ExperimentalUuidApi::class)
    actual suspend fun saveAlarm(alarmUIModel: AlarmUIModel) {
        val realm = RealmDBService.getRealm()

        realm.write {
            copyToRealm(AlarmDao().apply {
                this.id = Uuid.random().toString().replace(Regex("[^0-9]"), "").take(5)
                this.name = alarmUIModel.name
                this.isActive = alarmUIModel.isActive
                this.timeInMillis = alarmUIModel.timeInMillis
            })
        }
    }

    actual suspend fun getAlarms(): List<AlarmUIModel> {
        val realm = RealmDBService.getRealm()
        val res = realm.query<AlarmDao>().find()
        val alarmList = mutableListOf<AlarmUIModel>()

        res.forEach {
            alarmList.add(
                AlarmUIModel(
                    id = it.id,
                    name = it.name,
                    isActive = it.isActive,
                    timeInMillis = it.timeInMillis
                )
            )
        }

        return alarmList
    }

    actual suspend fun setAlarmState(alarmUIModel: AlarmUIModel) {
        val realm = RealmDBService.getRealm()

        realm.query<AlarmDao>("id == $0", alarmUIModel.id).find().firstOrNull()?.also {
            realm.write {
                findLatest(it)?.isActive = alarmUIModel.isActive
            }
        }

    }

    @SuppressLint("ScheduleExactAlarm")
    actual fun setAlarmTrigger(
        alarmUIModel: AlarmUIModel,
        trigger: Boolean
    ) {
        log("Setting Trigger")
        val context = ContextProvider.getContext()
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(ALARM_NAME, alarmUIModel.name)
        intent.putExtra(ALARM_TIME, alarmUIModel.timeInMillis ?: 0L)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmUIModel.id?.toIntOrNull() ?: 0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        if(trigger) {
            alarmUIModel.timeInMillis?.let {
                // Set the alarm
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    alarmUIModel.timeInMillis,
                    pendingIntent
                )
            }

            log("Trigger set")
        }
        else {
            log("Trigger cancelled")
            alarmManager.cancel(pendingIntent)
        }
    }
}