package com.simplx.apps.a30daystohabit.ui.add

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.reminder.AlarmScheduler
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_habit.*
import java.util.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AddHabitActivity : AppCompatActivity() {

    lateinit var viewModel: HabitsViewModel

    private var mTime: String = "000000"
    private var mHour: Int? = null
    private var mMinute: Int? = null

    private lateinit var checked: RadioButton
    private var notification: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        viewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)

        val mCalendar = Calendar.getInstance()
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
        mMinute = mCalendar.get(Calendar.MINUTE)

        title_toolbar.typeface = this.getTypeFace()
        labelTv2.typeface = this.getTypeFace()

        radio_group_id.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            checked = findViewById(checkedId)
            val radioButtonName = checked.text.toString()
            onRadioButtonClicked(radioButtonName)
        })

        if (determine_time_id.visibility == View.VISIBLE) {
            determine_time_id.setOnClickListener {

                determineTime()
            }
        }

        saveBtn_Id.setOnClickListener {
            if (habit_name_id.text.isNotEmpty() && habit_name_desc.text.isNotEmpty()) {
                if (notification) {
                    // CHECK TIME IS DETERMINED OR NOT.
                    if (time_id.text == "00:00 S") {
                        this.showToast(resources.getString(R.string.determine_time))
                    } else {
                        saveHabit()
                    }
                } else {
                    saveHabit()
                }
            } else {
                this.showToast(resources.getString(R.string.enter_all_fields))
            }
        }
    }

    private fun saveHabit() {

        val habit: Habit = Habit(
            name = habit_name_id.text.toString().trim(),
            desc = habit_name_desc.text.toString().trim(),
            notification = notification,
            time = mTime
        )

        viewModel.insertHabit(habit)
        this.showToast(resources.getString(R.string.habit_saved))

        if (notification) {
            this.showDelay(5000, resources.getString(R.string.wait_msg))
            Handler().postDelayed(this::setAlarm, 5000)
        }
    }

    private fun setAlarm() {
        // GET LAST HABIT AND SET ALARM BY IT.
        viewModel.lastInsertedHabit?.observe(this, Observer { cHabit ->
            if (cHabit != null) {
                val lastID: Int? = cHabit.ID
                lastID?.let {
                    AlarmScheduler.setAlarm(
                        minutes = mMinute,
                        hours = mHour,
                        habitName = cHabit.name,
                        requestHabitId = it,
                        motivationMsg = cHabit.desc,
                        context = this
                    )
                }
            } else {
                this.showToast(resources.getString(R.string.error))
            }
        })
    }

    private fun onRadioButtonClicked(key: String) {
        when (key) {
            "Yes" -> {
                time_layout_id.visibility = View.VISIBLE
                time_layout_id.startUiAnimation()
                notification = true
            }
            "No" -> {
                time_layout_id.visibility = View.GONE
                notification = false
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