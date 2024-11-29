package org.sujay.snoozeloo.core.crossplatform

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class AlarmDao: RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String? = null
    var isActive: Boolean? = null
    var timeInMillis: Long? = null
}