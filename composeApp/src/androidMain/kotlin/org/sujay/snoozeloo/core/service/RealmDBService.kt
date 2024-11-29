package org.sujay.snoozeloo.core.service

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.sujay.snoozeloo.core.crossplatform.AlarmDao

object RealmDBService {
    private lateinit var realm: Realm

    fun initialize() {
        val config = RealmConfiguration.Builder(setOf(AlarmDao::class))
            .schemaVersion(1) // Increment when your schema changes
            .build()
        realm = Realm.open(config)
    }

    fun getRealm(): Realm = realm
}