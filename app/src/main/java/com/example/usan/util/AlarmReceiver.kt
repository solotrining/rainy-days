package com.example.usan.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.usan.R
import com.example.usan.feature.main.MainActivity

class AlarmReceiver : BroadcastReceiver() {

    private var notificationManager : NotificationManager? = null

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun createNotificationChannel(context : Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.enableVibration(true)
        channel.description = "usan"
        notificationManager?.createNotificationChannel(channel)
    }

    private fun deliverNotification(context : Context) {
        val content = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            content,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.umbrella)
            .setContentTitle("우산")
            .setContentText("오늘 비가 오는지 확인 해 보세요!")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        notificationManager?.notify(NOTIFICATION_ID, builder.build())
    }

    companion object {
        private const val NOTIFICATION_ID = 0
        private const val CHANNEL_ID = "Alarm"
        private const val CHANNEL_NAME = "Notification"
    }
}