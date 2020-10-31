package com.simplx.apps.a30daystohabit.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.ui.add.AddHabitActivity
import com.simplx.apps.a30daystohabit.ui.trace.TracerActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setView()

        add_habit.setOnClickListener {
            startActivity(Intent(this, TracerActivity::class.java))
        }
    }

    private fun setView() {
        title_toolbar.typeface = HabitUtils.getTypeFace(this)
    }
}