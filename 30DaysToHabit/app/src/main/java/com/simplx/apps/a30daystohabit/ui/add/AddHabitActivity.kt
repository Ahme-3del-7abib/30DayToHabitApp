package com.simplx.apps.a30daystohabit.ui.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.factory.FactoryViewModel
import com.simplx.apps.a30daystohabit.model.HabitTracerViewModel
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import kotlinx.android.synthetic.main.activity_add_habit.*


class AddHabitActivity : AppCompatActivity() {

    lateinit var viewModel: HabitTracerViewModel
    private lateinit var checked: RadioButton
    private var notification: String = "No"
    private var time: String = "00"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        setView()

        radio_group_id.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            checked = findViewById(checkedId)
            notification = checked.text.toString()
            onRadioButtonClicked(notification)
        })

        saveBtn_Id.setOnClickListener {

            if (notification == "Yes") {
                time = time_id.text.toString()
            }

            var habit: Habit = Habit(
                name = habit_name_id.text.toString(),
                desc = habit_name_desc.text.toString(),
                notification = notification,
                time = time,
                progress = 0
            )

            if (habit_name_id.text.isNotEmpty() && habit_name_desc.text.isNotEmpty()) {
                viewModel.insertHabit(habit)
                HabitUtils.showToast(this, "Habit Saved.")
            } else {
                HabitUtils.showToast(this, "Name And Motivation Fields Is Required")
            }
        }
    }

    private fun setView() {

        title_toolbar.typeface = HabitUtils.getTypeFace(this)
        viewModel = ViewModelProviders.of(this, FactoryViewModel(this.application))
            .get(HabitTracerViewModel::class.java)
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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}