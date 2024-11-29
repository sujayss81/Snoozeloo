package org.sujay.snoozeloo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.sujay.snoozeloo.core.constant.AppConstants.ALARM_NAME
import org.sujay.snoozeloo.core.constant.AppConstants.ALARM_TIME
import org.sujay.snoozeloo.core.constant.AppConstants.IS_TRIGGERED

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isTriggered = intent.getBooleanExtra(IS_TRIGGERED, false)
        val alarmName = intent.getStringExtra(ALARM_NAME) ?: ""
        val alarmTime = intent.getLongExtra(ALARM_TIME, 0L)

        installSplashScreen()
        setContent {
            App(isTriggered, alarmName, alarmTime)
        }
    }
}