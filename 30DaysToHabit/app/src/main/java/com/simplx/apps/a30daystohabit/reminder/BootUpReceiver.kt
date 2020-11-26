package com.simplx.apps.a30daystohabit.reminder

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.simplx.apps.a30daystohabit.ui.main.MainActivity

class BootUpReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val intent1 = Intent(context, MainActivity::class.java)
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(intent1)
    }
}