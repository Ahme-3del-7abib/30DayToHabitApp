package com.simplx.apps.a30daystohabit.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.ui.add.AddHabitActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_habit.setOnClickListener {
            startActivity(Intent(this, AddHabitActivity::class.java))
        }
    }
}