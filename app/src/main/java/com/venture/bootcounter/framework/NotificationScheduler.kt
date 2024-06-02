package com.venture.bootcounter.framework

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.concurrent.TimeUnit

object NotificationScheduler {
    private const val NOTIFICATION_INTERVAL = 15L

    fun scheduleRepeatingNotifications(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val intervalMillis = TimeUnit.MINUTES.toMillis(NOTIFICATION_INTERVAL)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalMillis, intervalMillis, pendingIntent
        )
    }

    fun cancelNotifications(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.cancel(pendingIntent)
    }
}
