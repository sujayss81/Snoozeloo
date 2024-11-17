package org.sujay.snoozeloo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform