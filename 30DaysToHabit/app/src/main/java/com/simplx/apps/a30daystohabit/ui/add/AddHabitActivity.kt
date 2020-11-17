package com.simplx.apps.a30daystohabit.ui.add

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.factory.FactoryViewModel
import com.simplx.apps.a30daystohabit.model.HabitTracerViewModel
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.reminder.AlarmScheduler
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import kotlinx.android.synthetic.main.activity_add_habit.*
import java.util.*

class AddHabitActivity : AppCompatActivity() {

    lateinit var viewModel: HabitTracerViewModel

    private var mTime: String = "000000"
    private var mHour: Int? = null
    private var mMinute: Int? = null

    private lateinit var checked: RadioButton
    private var notification: String = "No"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        setView()

        radio_group_id.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            checked = findViewById(checkedId)
            notification = checked.text.toString()
            onRadioButtonClicked(notification)
        })

        if (determine_time_id.visibility == View.VISIBLE) {
            determine_time_id.setOnClickListener {

                determineTime()
            }
        }

        saveBtn_Id.setOnClickListener {
            if (habit_name_id.text.isNotEmpty() && habit_name_desc.text.isNotEmpty()) {
                if (notification == "No") {
                    saveHabit(notify = "No")
                } else {
                    if (time_id.text == "00:00 S") {
                        HabitUtils.showToast(this, "Determine Notification Time.")
                    } else {
                        saveHabit(notify = "Yes")
                    }
                }
            } else {
                HabitUtils.showToast(this, "Habit Name And Motivation Are Required.")
            }
        }
    }

    private fun setView() {

        viewModel = ViewModelProviders.of(this, FactoryViewModel(this.application))
            .get(HabitTracerViewModel::class.java)

        val mCalendar = Calendar.getInstance()
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
        mMinute = mCalendar.get(Calendar.MINUTE)

        title_toolbar.typeface = HabitUtils.getTypeFace(this)
        labelTv2.typeface = HabitUtils.getTypeFace(this)

    }

    private fun saveHabit(notify: String) {

        val habit: Habit = Habit(
            name = habit_name_id.text.toString().trim(),
            desc = habit_name_desc.text.toString().trim(),
            notification = notification,
            time = mTime
        )

        viewModel.insertHabit(habit)
        HabitUtils.showToast(this, "Habit Saved.")

        createDaysAndSetAlarm(notify)
    }

    private fun createDaysAndSetAlarm(notify: String) {

        viewModel.currentHabit?.observe(this, Observer {

            if (notify == "Yes") {
                AlarmScheduler.setAlarm(
                    minutes = mMinute,
                    hours = mHour,
                    habitName = it.name,
                    requestHabitId = it.ID!!,
                    motivationMsg = it.desc,
                    context = this
                )
            }

            val day: Days = Days(
                habit_id = it.ID!!,
                day_one = "wait",
                day_two = "wait",
                day_three = "wait",
                day_four = "wait",
                day_five = "wait",
                day_six = "wait",
                day_seven = "wait",
                day_eight = "wait",
                day_nine = "wait",
                day_ten = "wait",
                day_eleven = "wait",
                day_twelve = "wait",
                day_thirteen = "wait",
                day_fourteen = "wait",
                day_fifteen = "wait",
                day_sixteen = "wait",
                day_seventeen = "wait",
                day_eighteen = "wait",
                day_nineteen = "wait",
                day_twenty = "wait",
                day_twenty_one = "wait",
                day_twenty_two = "wait",
                day_twenty_three = "wait",
                day_twenty_four = "wait",
                day_twenty_five = "wait",
                day_twenty_six = "wait",
                day_twenty_seven = "wait",
                day_twenty_eight = "wait",
                day_twenty_nine = "wait",
                day_thirty = "wait",
                progress = 0.0,
                successDays = 0,
                failedDays = 0,
                currentDay = "day_one_id"
            )

            viewModel.insertDay(day)
        })
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

    private fun determineTime() {

        val calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)

        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
            this,
            OnTimeSetListener { _, selectedHour, selectedMinute ->

                mMinute = selectedMinute
                mHour = selectedHour

                time_id.text = HabitUtils.formatTime(selectedHour, selectedMinute)
                mTime = time_id.text.toString()

            }, hour, minute, false
        )
        mTimePicker.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}