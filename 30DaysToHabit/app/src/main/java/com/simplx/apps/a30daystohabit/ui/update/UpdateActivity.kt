package com.simplx.apps.a30daystohabit.ui.update

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProviders
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.factory.FactoryViewModel
import com.simplx.apps.a30daystohabit.model.HabitTracerViewModel
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.reminder.AlarmScheduler
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update.radio_group_id
import kotlinx.android.synthetic.main.activity_update.time_layout_id
import java.util.*

class UpdateActivity : AppCompatActivity() {


    lateinit var viewModel: HabitTracerViewModel
    private lateinit var checked: RadioButton

    private var habitId: Int? = null
    private var habitName: String? = null
    private var habitDesc: String? = null
    private var notification: String? = null
    private var time: String? = null

    private var mHour: Int? = null
    private var mMinute: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        getIntentData()

        setView()

        radio_group_id.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            checked = findViewById(checkedId)
            notification = checked.text.toString()
            onRadioButtonClicked(notification)
        })

        if (determine_time_update_id.visibility == View.VISIBLE) {
            determine_time_update_id.setOnClickListener {

                determineTime()
            }
        }

        updateBtn.setOnClickListener {
            if (habit_name_update_id.text.isNotEmpty() && habit_desc_update_id.text.isNotEmpty()) {
                if (notification == "No") {
                    AlarmScheduler.cancelAlarm(context = this, requestHabitId = habitId!!)
                    updateHabit(notify = "No")
                } else {
                    if (time_update_id.text == time) {
                        updateHabit(notify = "No")
                    } else {
                        if (time_update_id.text == "00:00 S") {
                            HabitUtils.showToast(this, "Determine Notification Time.")
                        } else {
                            updateHabit(notify = "Yes")
                        }
                    }
                }
            } else {
                HabitUtils.showToast(this, "Habit Name And Motivation Are Required.")
            }
        }
    }

    private fun getIntentData() {
        habitId = intent.extras?.getInt("id")
        habitName = intent.extras?.getString("name")
        habitDesc = intent.extras?.getString("desc")
        notification = intent.extras?.getString("notification")
        time = intent.extras?.getString("time")
    }

    private fun setView() {

        viewModel = ViewModelProviders.of(this, FactoryViewModel(this.application))
            .get(HabitTracerViewModel::class.java)

        habit_name_update_id.setText(habitName)
        habit_desc_update_id.setText(habitDesc)

        if (notification == "Yes") {
            yes_radio_update_id.isChecked = true
            no_radio_update_id.isChecked = false
            time_layout_id.visibility = View.VISIBLE
            time_update_id.text = time

        } else {
            time_layout_id.visibility = View.GONE
            no_radio_update_id.isChecked = true
            yes_radio_update_id.isChecked = false
        }

        title_toolbar_update.typeface = HabitUtils.getTypeFace(this)
        labelTv.typeface = HabitUtils.getTypeFace(this)
    }

    private fun updateHabit(notify: String) {

        if (notify == "Yes") {
            updateAlarm()
        }

        var habit: Habit = Habit(
            ID = habitId,
            name = habit_name_update_id.text.toString().trim(),
            desc = habit_desc_update_id.text.toString().trim(),
            notification = notification!!,
            time = time!!
        )

        viewModel.updateHabit(habit)
        HabitUtils.showToast(this, "Habit Updated.")
    }

    private fun updateAlarm() {

        AlarmScheduler.cancelAlarm(context = this, requestHabitId = habitId!!)

        AlarmScheduler.setAlarm(
            minutes = mMinute,
            hours = mHour,
            habitName = habit_name_update_id.text.toString().trim(),
            requestHabitId = habitId!!,
            motivationMsg = habit_desc_update_id.text.toString().trim(),
            context = this
        )
    }

    private fun determineTime() {

        val calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)

        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->

                mHour = selectedHour
                mMinute = selectedMinute

                time_update_id.text = HabitUtils.formatTime(selectedHour, selectedMinute)
                time = time_update_id.text.toString()

            }, hour, minute, false
        )
        mTimePicker.show()
    }

    private fun onRadioButtonClicked(key: String?) {

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