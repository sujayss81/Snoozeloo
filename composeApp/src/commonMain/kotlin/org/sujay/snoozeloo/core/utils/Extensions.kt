package org.sujay.snoozeloo.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

fun LocalDateTime.formattedAlarmTime(): String {
    val hour = if (this.hour % 12 == 0) 12 else this.hour % 12
    return "${hour}:${this.minute}"
}


fun LocalDateTime.getAmPm(): String = if (this.hour >= 12) "PM" else "AM"

fun LocalDateTime.getAlarmInText(): String? {
    val currentInstant = Clock.System.now()
    val targetInstant = this.toInstant(TimeZone.currentSystemDefault())

    // Calculate the difference in seconds
    val durationSeconds = targetInstant.epochSeconds - currentInstant.epochSeconds
    if (durationSeconds <= 0) return null

    val hours = durationSeconds / 3600
    val minutes = (durationSeconds % 3600) / 60

    return buildString {
        append("Alarm in ")
        if (hours > 0) append("${hours}h ")
        if (minutes > 0) append("${minutes}min")
    }.trim()
}