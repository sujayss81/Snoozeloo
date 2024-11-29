package org.sujay.snoozeloo.core.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.sujay.snoozeloo.MainActivity
import org.sujay.snoozeloo.core.constant.AppConstants.ALARM_NAME
import org.sujay.snoozeloo.core.constant.AppConstants.ALARM_TIME
import org.sujay.snoozeloo.core.constant.AppConstants.IS_TRIGGERED

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmName = intent?.getStringExtra(ALARM_NAME)
        val alarmTime = intent?.getLongExtra(ALARM_TIME, 0L)

        val triggerIntent = Intent(context, MainActivity::class.java)
        triggerIntent.putExtra(IS_TRIGGERED, true)
        triggerIntent.putExtra(ALARM_NAME, alarmName)
        triggerIntent.putExtra(ALARM_TIME, alarmTime)

        triggerIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

//        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
//        val ringtone = RingtoneManager.getRingtone(context, ringtoneUri)
//        ringtone.play()

        context?.startActivity(triggerIntent)
    }

}