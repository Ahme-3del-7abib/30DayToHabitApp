package com.simplx.apps.a30daystohabit.reminder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.ui.notification.NotificationMessageActivity

class AlarmBroadCast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent!!.extras
        val habitName = bundle?.getString("name")
        val habitMsg = bundle?.getString("motivation")
        val habitId = bundle?.getInt("habit_id")

        // -- When Click on Notification
        val intent1 = Intent(context, NotificationMessageActivity::class.java)
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent1.putExtra("name", habitName)
        intent1.putExtra("motivation", habitMsg)
        intent1.putExtra("habit_id", habitId)

        // -- Build Notification
        val pendingIntent =
            PendingIntent.getActivity(
                context,
                habitId!!,
                intent1,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val mBuilder = NotificationCompat.Builder(context, "notify_001")

        val contentView =
            RemoteViews(context.packageName, R.layout.notification_layout)

        contentView.setTextViewText(R.id.habit_notify_id, habitName)
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher)

        mBuilder.setSmallIcon(R.drawable.ic_baseline_alarm_on_24)
        mBuilder.setAutoCancel(true)
        mBuilder.setOngoing(true)

        mBuilder.priority = Notification.PRIORITY_HIGH
        mBuilder.setOnlyAlertOnce(true)
        mBuilder.build().flags = Notification.FLAG_NO_CLEAR or Notification.PRIORITY_HIGH

        mBuilder.setContent(contentView)
        mBuilder.setContentIntent(pendingIntent)

        // -- for oreo android version and ++
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "channel_id"
            val channel =
                NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
            mBuilder.setChannelId(channelId)
        }

        val notification = mBuilder.build()
        notificationManager.notify(99, notification)
    }
}