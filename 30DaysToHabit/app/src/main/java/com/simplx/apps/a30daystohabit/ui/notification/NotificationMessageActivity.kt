package com.simplx.apps.a30daystohabit.ui.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.ui.trace.TracerActivity
import kotlinx.android.synthetic.main.activity_notification_message.*

class NotificationMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_message)

        val bundle = intent.extras

        val habitId = bundle?.getInt("id")
        val habitName = bundle?.getString("name")
        val motivation = bundle?.getString("motivation")

        habit_name_motivation_id.text = habitName
        habit_motivation_id.text = motivation

        motivationBtn.setOnClickListener {
            startActivity(
                Intent(this, TracerActivity::class.java)
                    .putExtra("id", habitId)
                    .putExtra("name", habitName)
            )
        }
    }
}