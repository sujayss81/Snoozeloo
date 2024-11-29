package org.sujay.snoozeloo.core.crossplatform

import io.realm.kotlin.ext.query
import org.sujay.snoozeloo.core.service.RealmDBService
import org.sujay.snoozeloo.data.AlarmUIModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

actual class AlarmManager {
    @OptIn(ExperimentalUuidApi::class)
    actual suspend fun saveAlarm(alarmUIModel: AlarmUIModel) {
        val realm = RealmDBService.getRealm()

        realm.write {
            copyToRealm(AlarmDao().apply {
                this.id = Uuid.random().toString()
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
            alarmList.add(AlarmUIModel(
                id = it.id,
                name = it.name,
                isActive = it.isActive,
                timeInMillis = it.timeInMillis
            ))
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

}