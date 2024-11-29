package org.sujay.snoozeloo.core.crossplatform

import android.util.Log

actual fun log(msg: String) {
    Log.d("$$$", msg)
}