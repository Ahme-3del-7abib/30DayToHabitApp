package com.simplx.apps.a30daystohabit.ui.update

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.reminder.AlarmScheduler
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import com.simplx.apps.a30daystohabit.utils.getTypeFace
import com.simplx.apps.a30daystohabit.utils.showToast
import com.simplx.apps.a30daystohabit.utils.startUiAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update.radio_group_id
import kotlinx.android.synthetic.main.activity_update.time_layout_id
import java.util.*

@AndroidEntryPoint
class UpdateActivity : AppCompatActivity() {

    lateinit var viewModel: UpdateViewModel
    private lateinit var checked: RadioButton

    private var habitId: Int? = null
    private var habitName: String? = null
    private var habitDesc: String? = null
    private var notification: Boolean? = null
    private var time: String? = null

    private var mHour: Int? = null
    private var mMinute: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        viewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)

        habitId = intent.extras?.getInt(resources.getString(R.string.id))
        habitName = intent.extras?.getString(resources.getString(R.string.name))
        habitDesc = intent.extras?.getString(resources.getString(R.string.desc))
        notification = intent.extras?.getBoolean(resources.getString(R.string.notification))
        time = intent.extras?.getString(resources.getString(R.string.time))

        this.showToast(" id = $habitId")

        if (notification!!) {
            yes_radio_update_id.isChecked = true
            no_radio_update_id.isChecked = false
            time_layout_id.visibility = View.VISIBLE
            time_update_id.text = time

        } else {
            time_layout_id.visibility = View.GONE
            no_radio_update_id.isChecked = true
            yes_radio_update_id.isChecked = false
        }

        habit_name_update_id.setText(habitName)
        habit_desc_update_id.setText(habitDesc)

        title_toolbar_update.typeface = this.getTypeFace()
        labelTv.typeface = this.getTypeFace()

        radio_group_id.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            checked = findViewById(checkedId)
            val radioButtonName = checked.text.toString()
            onRadioButtonClicked(radioButtonName)
        })

        if (determine_time_update_id.visibility == View.VISIBLE) {
            determine_time_update_id.setOnClickListener {

                determineTime()
            }
        }

        updateBtn.setOnClickListener {
            if (habit_name_update_id.text.isNotEmpty() && habit_desc_update_id.text.isNotEmpty()) {
                if (notification!!) {
                    if (time_update_id.text == time) {
                        updateHabit()
                    } else {
                        if (time_update_id.text == "00:00 S") {
                            this.showToast(resources.getString(R.string.determine_time))
                        } else {
                            updateHabit()
                        }
                    }
                } else {
                    AlarmScheduler.cancelAlarm(context = this, requestHabitId = habitId!!)
                    updateHabit()
                }
            } else {
                this.showToast(resources.getString(R.string.enter_all_fields))
            }
        }
    }

    private fun updateHabit() {

        val habit: Habit? = habitId?.let {
            Habit(
                ID = it,
                name = habit_name_update_id.text.toString().trim(),
                desc = habit_desc_update_id.text.toString().trim(),
                notification = notification!!,
                time = time!!
            )
        }

        habit?.let { viewModel.updateHabit(it) }
        this.showToast(resources.getString(R.string.habit_updated))

        if (notification!!) {
            updateAlarm()
        }
    }

    private fun updateAlarm() {

        habitId?.let { AlarmScheduler.cancelAlarm(context = this, requestHabitId = it) }

        habitId?.let {
            AlarmScheduler.setAlarm(
                minutes = mMinute,
                hours = mHour,
                habitName = habit_name_update_id.text.toString().trim(),
                requestHabitId = it,
                motivationMsg = habit_desc_update_id.text.toString().trim(),
                context = this
            )
        }
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
                time_layout_id.startUiAnimation()
                notification = true
            }
            "No" -> {
                time_layout_id.visibility = View.GONE
                notification = false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }

}