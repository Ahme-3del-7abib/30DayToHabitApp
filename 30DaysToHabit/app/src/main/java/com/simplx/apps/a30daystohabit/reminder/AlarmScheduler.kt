package com.simplx.apps.a30daystohabit.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.text.ParseException
import java.util.*

class AlarmScheduler {

    companion object {

        fun setAlarm(
            minutes: Int?,
            hours: Int?,
            habitName: String,
            requestHabitId: Int,
            motivationMsg: String,
            context: Context
        ) {

            val manager = AlarmManagerProvider.getAlarmManager(context)

            var intent = Intent(context, AlarmBroadCast::class.java)

            intent.putExtra("name", habitName)
            intent.putExtra("habit_id", requestHabitId)
            intent.putExtra("motivation", motivationMsg)

            val calendar: Calendar = Calendar.getInstance()

            hours?.let { calendar.set(Calendar.HOUR_OF_DAY, it) }
            minutes?.let { calendar.set(Calendar.MINUTE, it) }
            calendar.set(Calendar.SECOND, 10)

            val selectedTimestamp = calendar.timeInMillis

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestHabitId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            try {
                manager?.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    selectedTimestamp,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        fun cancelAlarm(context: Context, requestHabitId: Int) {

            val manager = AlarmManagerProvider.getAlarmManager(context)
            val intent: Intent = Intent(context, AlarmBroadCast::class.java)

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestHabitId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            if (pendingIntent != null) {
                pendingIntent.cancel()
                manager?.cancel(pendingIntent)
            }
        }
    }
}