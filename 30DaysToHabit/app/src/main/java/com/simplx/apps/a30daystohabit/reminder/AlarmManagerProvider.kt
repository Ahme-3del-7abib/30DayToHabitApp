package com.simplx.apps.a30daystohabit.reminder

import android.app.AlarmManager
import android.content.Context


class AlarmManagerProvider {

    companion object {

        private var sAlarmManager: AlarmManager? = null

        @Synchronized
        fun getAlarmManager(context: Context): AlarmManager? {
            if (sAlarmManager == null) {
                sAlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            }

            return sAlarmManager
        }
    }
}