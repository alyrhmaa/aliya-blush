package com.example.aliya_blush.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.aliya_blush.utils.NotificationHelper
import com.example.aliya_blush.BaseActivity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        val title =
            intent.getStringExtra("title")
                ?: "Reminder Desa"

        val message =
            intent.getStringExtra("message")
                ?: "Periksa usulan desa"

        val targetClassName =
            intent.getStringExtra("target_activity")

        val targetIntent =
            if (!targetClassName.isNullOrEmpty()) {

                val clazz = Class.forName(targetClassName)

                Intent(context, clazz).apply {
                    flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

            } else {

                Intent(context, BaseActivity::class.java)
            }

        NotificationHelper.showNotification(
            context,
            title,
            message,
            targetIntent
        )
    }
}