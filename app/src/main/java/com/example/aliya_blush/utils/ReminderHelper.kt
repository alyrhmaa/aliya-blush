package com.example.aliya_blush.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

object ReminderHelper {

    fun setReminder(
        context: Context,
        minutes: Int,
        title: String,
        message: String,
        targetActivity: Class<*>
    ) {

        val calendar = Calendar.getInstance()

        calendar.add(Calendar.MINUTE, minutes)

        val intent =
            Intent(context, ReminderReceiver::class.java).apply {

                putExtra("title", title)

                putExtra("message", message)

                putExtra(
                    "target_activity",
                    targetActivity.name
                )
            }

        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
            )

        val alarmManager =
            context.getSystemService(
                Context.ALARM_SERVICE
            ) as AlarmManager

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}