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
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import kotlinx.android.synthetic.main.activity_add_habit.*
import java.util.*

class AddHabitActivity : AppCompatActivity() {

    lateinit var viewModel: HabitTracerViewModel
    private lateinit var checked: RadioButton
    private var notification: String = "No"
    private var time: String = "0000"
    var saveBtn: String? = null

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

            if (habit_name_id.text.isNotEmpty() && habit_name_desc.text.isNotEmpty()) {

                if (saveBtn.equals("add")) {
                    saveHabit()
                    startActivity(Intent(this, MainActivity::class.java))

                } else if (saveBtn.equals("update")) {
                    updateHabit()
                }

            } else {
                HabitUtils.showToast(this, "Name And Motivation Fields Are Required")
            }
        }

        if (determine_time_id.visibility == View.VISIBLE) {
            determine_time_id.setOnClickListener {

                val calendar = Calendar.getInstance()
                val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
                val minute: Int = calendar.get(Calendar.MINUTE)

                val mTimePicker: TimePickerDialog
                mTimePicker = TimePickerDialog(
                    this,
                    OnTimeSetListener { _, selectedHour, selectedMinute ->
                        if (selectedMinute < 10) {
                            time = "$selectedHour:0$selectedMinute"
                            time_id.text = "$selectedHour:0$selectedMinute"
                        } else {
                            time = "$selectedHour:$selectedMinute"
                            time_id.text = "$selectedHour:$selectedMinute"
                        }
                    }, hour, minute, false
                )
                mTimePicker.show()
            }
        }
    }

    private fun saveHabit() {

        val habit: Habit = Habit(
            name = habit_name_id.text.toString(),
            desc = habit_name_desc.text.toString(),
            notification = notification,
            time = time
        )

        viewModel.insertHabit(habit)
        HabitUtils.showToast(this, "Habit Saved.")

        createDaysForThisHabit()
    }

    private fun createDaysForThisHabit() {

        viewModel.currentHabit?.observe(this, Observer {
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

    private fun updateHabit() {

        var habit: Habit = Habit(
            ID = intent.extras?.getInt("id"),
            name = habit_name_id.text.toString(),
            desc = habit_name_desc.text.toString(),
            notification = notification,
            time = time
        )

        viewModel.updateHabit(habit)
        HabitUtils.showToast(this, "Habit Updated.")
    }

    private fun setView() {

        viewModel = ViewModelProviders.of(this, FactoryViewModel(this.application))
            .get(HabitTracerViewModel::class.java)

        title_toolbar.typeface = HabitUtils.getTypeFace(this)

        saveBtn = intent.extras?.getString("Button")

        if (saveBtn.equals("add")) {

            saveBtn_Id.text = "SAVE"
            title_toolbar.text = "Add New Habit"

        } else if (saveBtn.equals("update")) {

            saveBtn_Id.text = "UPDATE"
            title_toolbar.text = "Update Habit"

            habit_name_id.setText(intent.extras?.getString("name"))
            habit_name_desc.setText(intent.extras?.getString("desc"))

            if (intent.extras?.getString("notification").equals("Yes")) {
                yes_radio_id.isChecked = true
                no_radio_id.isChecked = false
                time_layout_id.visibility = View.VISIBLE
                time_id.text = intent.extras?.getString("time")

            } else {
                time_layout_id.visibility = View.GONE
                no_radio_id.isChecked = true
                yes_radio_id.isChecked = false
            }
        }
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