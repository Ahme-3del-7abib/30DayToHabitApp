package com.simplx.apps.a30daystohabit.ui.add

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import kotlinx.android.synthetic.main.activity_add_habit.*

class AddHabitActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        setView()

        radio_group_id.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            var checked: RadioButton = findViewById(checkedId)
            onRadioButtonClicked(checked.text.toString())

        })
    }

    private fun setView() {
        title_toolbar.typeface = HabitUtils.getTypeFace(this)
    }

    private fun onRadioButtonClicked(key: String) {

        when (key) {
            "Yes" -> {
                time_layout_id.visibility = View.VISIBLE
                HabitUtils.setAnimation(time_layout_id)
            }
            "No" -> {
                time_layout_id.visibility = View.GONE
            }
        }
    }
}