package org.sujay.snoozeloo.data

data class AlarmUIModel(
    val id: String? = null,
    val name: String? = null,
    var isActive: Boolean? = null,
    val timeInMillis: Long? = null
)
