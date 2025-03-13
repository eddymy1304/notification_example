package com.eddymy1304.notification_example.ui

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.eddymy1304.notification_example.MainActivity
import com.eddymy1304.notification_example.MainActivity.Companion.CHANNEL_ID
import com.eddymy1304.notification_example.R

class AlarmNotification : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = 1
    }

    override fun onReceive(context: Context, intent: Intent?) {
        createSimpleNotification(context)
    }


    private fun createSimpleNotification(context: Context) {

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat
            .Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.outline_favorite_border_24)
            .setContentTitle(context.getString(R.string.title_notification))
            .setStyle(
                NotificationCompat
                    .BigTextStyle()
                    .bigText(context.getString(R.string.text_large_notification))
                    .setSummaryText(context.getString(R.string.text_notification))
            )
            .setContentText(context.getString(R.string.text_notification))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }
}