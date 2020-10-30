package com.simplx.apps.a30daystohabit.ui.add

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simplx.apps.a30daystohabit.R
import kotlinx.android.synthetic.main.activity_add_habit.*

class AddHabitActivity : AppCompatActivity() {


    lateinit var animation: AlphaAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        setView()

        radio_group_id.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            var id = group.checkedRadioButtonId
            var checked: RadioButton = findViewById(checkedId)
            onRadioButtonClicked(checked.text.toString())

        })
    }

    private fun setView() {
        animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 1500
    }

    private fun onRadioButtonClicked(key: String) {

        when (key) {
            "Yes" -> {
                time_layout_id.visibility = View.VISIBLE
                time_layout_id.startAnimation(animation)
            }
            "No" -> {
                time_layout_id.visibility = View.GONE
            }
        }
    }
}