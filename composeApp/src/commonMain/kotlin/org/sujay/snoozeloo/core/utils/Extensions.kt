package org.sujay.snoozeloo.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.*
import kotlin.time.Duration.Companion.days

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

fun Int.get0AppendedTime(): String =
    if (this in 0..9)
        "0$this"
    else
        "$this"

fun Int.getEpochMillisFromTime(selectedHours: Int?, selectedMinutes: Int?): Long {
    // Get the current date and time
    val now = Clock.System.now()
    val today = now.toLocalDateTime(TimeZone.currentSystemDefault()).date

    // Combine date with selected time
    val alarmTime = LocalDateTime(
        year = today.year,
        month = today.month,
        dayOfMonth = today.dayOfMonth,
        hour = selectedHours ?: 0,
        minute = selectedMinutes ?: 0
    )

    // If the selected time has already passed, set it for the next day
    val alarmInstant = if (alarmTime > now.toLocalDateTime(TimeZone.currentSystemDefault())) {
        alarmTime.toInstant(TimeZone.currentSystemDefault())
    } else {
        alarmTime.toInstant(TimeZone.currentSystemDefault()).plus(1.days)
    }

    // Convert to epoch milliseconds
    return alarmInstant.toEpochMilliseconds()
}

fun Long.getTimeUntilAlarm(): String? {
    val currentInstant = Clock.System.now() // Get the current time as Instant
    val alarmInstant = Instant.fromEpochMilliseconds(this) // Convert the alarm epoch to Instant

    // Calculate the difference in milliseconds
    val timeDifferenceMillis = alarmInstant.toEpochMilliseconds() - currentInstant.toEpochMilliseconds()

    // Calculate the hours and minutes from the time difference
    val hours = timeDifferenceMillis / (1000 * 60 * 60) // Convert milliseconds to hours
    val minutes = (timeDifferenceMillis % (1000 * 60 * 60)) / (1000 * 60) // Convert remaining milliseconds to minutes

    // Build the string based on the duration
    return if (hours > 0) {
        "Alarm in ${hours}h ${minutes}min"
    } else if (minutes > 0) {
        "Alarm in ${minutes}min"
    } else {
        null
    }
}
