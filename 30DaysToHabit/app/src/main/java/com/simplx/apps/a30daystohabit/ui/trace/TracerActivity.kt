package com.simplx.apps.a30daystohabit.ui.trace

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.factory.FactoryViewModel
import com.simplx.apps.a30daystohabit.model.HabitTracerViewModel
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import kotlinx.android.synthetic.main.activity_tracert.*


class TracerActivity : AppCompatActivity() {

    lateinit var viewModel: HabitTracerViewModel
    private var habitId: Int? = null
    private var habitName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracert)

        setInstances()

        setView()

        daysActionButtons()

    }

    private fun setInstances() {

        viewModel = ViewModelProvider(this, FactoryViewModel(this.application))
            .get(HabitTracerViewModel::class.java)

        habitId = intent.extras?.getInt("id")
        habitName = intent.extras?.getString("name")

    }

    private fun setView() {

        ac_Tv.typeface = HabitUtils.getTypeFace(this)

        viewModel.getCurrentDayById(habitId!!)
        viewModel.returnedVal.observe(this, Observer {

            determinateBar.progress = it.progress.toInt()
            achievementNumId.text = it.progress.toInt().toString()
            successDayNumId.text = it.successDays.toString()
            failedDaysNumId.text = it.failedDays.toString()

            if (it.currentDay == "NOTHING") {
                showAlertDialogButtonClicked("FINISHED")
            } else {
                getCurrentDayTv(it.currentDay).setBackgroundResource(R.drawable.current_day_background)
            }

            if (it.day_one == "success") {
                day_one_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_two_id.isEnabled = true
                day_one_id.isEnabled = false

            } else if (it.day_one == "failed") {
                day_one_id.setBackgroundResource(R.drawable.day_background_red)
                day_two_id.isEnabled = true
                day_one_id.isEnabled = false
            }

            if (it.day_two == "success") {
                day_two_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_three_id.isEnabled = true
                day_two_id.isEnabled = false

            } else if (it.day_two == "failed") {
                day_two_id.setBackgroundResource(R.drawable.day_background_red)
                day_three_id.isEnabled = true
                day_two_id.isEnabled = false
            }

            if (it.day_three == "success") {
                day_three_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_four_id.isEnabled = true
                day_three_id.isEnabled = false

            } else if (it.day_three == "failed") {
                day_three_id.setBackgroundResource(R.drawable.day_background_red)
                day_four_id.isEnabled = true
                day_three_id.isEnabled = false
            }

            if (it.day_four == "success") {
                day_four_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_five_id.isEnabled = true
                day_four_id.isEnabled = false

            } else if (it.day_four == "failed") {
                day_four_id.setBackgroundResource(R.drawable.day_background_red)
                day_five_id.isEnabled = true
                day_four_id.isEnabled = false
            }

            if (it.day_five == "success") {
                day_five_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_six_id.isEnabled = true
                day_five_id.isEnabled = false

            } else if (it.day_five == "failed") {
                day_five_id.setBackgroundResource(R.drawable.day_background_red)
                day_six_id.isEnabled = true
                day_five_id.isEnabled = false
            }

            if (it.day_six == "success") {
                day_six_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_seven_id.isEnabled = true
                day_six_id.isEnabled = false

            } else if (it.day_six == "failed") {
                day_six_id.setBackgroundResource(R.drawable.day_background_red)
                day_seven_id.isEnabled = true
                day_six_id.isEnabled = false
            }

            if (it.day_seven == "success") {
                day_seven_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_eight_id.isEnabled = true
                day_seven_id.isEnabled = false

            } else if (it.day_seven == "failed") {
                day_seven_id.setBackgroundResource(R.drawable.day_background_red)
                day_eight_id.isEnabled = true
                day_seven_id.isEnabled = false
            }

            if (it.day_eight == "success") {
                day_eight_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_nine_id.isEnabled = true
                day_eight_id.isEnabled = false

            } else if (it.day_eight == "failed") {
                day_eight_id.setBackgroundResource(R.drawable.day_background_red)
                day_nine_id.isEnabled = true
                day_eight_id.isEnabled = false
            }

            if (it.day_nine == "success") {
                day_nine_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_ten_id.isEnabled = true
                day_nine_id.isEnabled = false

            } else if (it.day_nine == "failed") {
                day_nine_id.setBackgroundResource(R.drawable.day_background_red)
                day_ten_id.isEnabled = true
                day_nine_id.isEnabled = false
            }

            if (it.day_ten == "success") {
                day_ten_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_eleven_id.isEnabled = true
                day_ten_id.isEnabled = false

            } else if (it.day_ten == "failed") {
                day_ten_id.setBackgroundResource(R.drawable.day_background_red)
                day_eleven_id.isEnabled = true
                day_ten_id.isEnabled = false
            }

            if (it.day_eleven == "success") {
                day_eleven_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_tweleve_id.isEnabled = true
                day_eleven_id.isEnabled = false

            } else if (it.day_eleven == "failed") {
                day_eleven_id.setBackgroundResource(R.drawable.day_background_red)
                day_tweleve_id.isEnabled = true
                day_eleven_id.isEnabled = false
            }

            if (it.day_twelve == "success") {
                day_tweleve_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_thirteen_id.isEnabled = true
                day_tweleve_id.isEnabled = false

            } else if (it.day_twelve == "failed") {
                day_tweleve_id.setBackgroundResource(R.drawable.day_background_red)
                day_thirteen_id.isEnabled = true
                day_tweleve_id.isEnabled = false
            }

            if (it.day_thirteen == "success") {
                day_thirteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_fourteen_id.isEnabled = true
                day_thirteen_id.isEnabled = false

            } else if (it.day_thirteen == "failed") {
                day_thirteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_fourteen_id.isEnabled = true
                day_thirteen_id.isEnabled = false
            }

            if (it.day_fourteen == "success") {
                day_fourteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_fifteen_id.isEnabled = true
                day_fourteen_id.isEnabled = false

            } else if (it.day_fourteen == "failed") {
                day_fourteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_fifteen_id.isEnabled = true
                day_fourteen_id.isEnabled = false
            }

            if (it.day_fifteen == "success") {
                day_fifteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_sixteen_id.isEnabled = true
                day_fifteen_id.isEnabled = false

            } else if (it.day_fifteen == "failed") {
                day_fifteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_sixteen_id.isEnabled = true
                day_fifteen_id.isEnabled = false
            }

            if (it.day_sixteen == "success") {
                day_sixteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_seventeen_id.isEnabled = true
                day_sixteen_id.isEnabled = false

            } else if (it.day_sixteen == "failed") {
                day_sixteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_seventeen_id.isEnabled = true
                day_sixteen_id.isEnabled = false
            }

            if (it.day_seventeen == "success") {
                day_seventeen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_eighteen_id.isEnabled = true
                day_seventeen_id.isEnabled = false

            } else if (it.day_seventeen == "failed") {
                day_seventeen_id.setBackgroundResource(R.drawable.day_background_red)
                day_eighteen_id.isEnabled = true
                day_seventeen_id.isEnabled = false
            }

            if (it.day_eighteen == "success") {
                day_eighteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_nineteen_id.isEnabled = true
                day_eighteen_id.isEnabled = false

            } else if (it.day_eighteen == "failed") {
                day_eighteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_nineteen_id.isEnabled = true
                day_eighteen_id.isEnabled = false
            }

            if (it.day_nineteen == "success") {
                day_nineteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_id.isEnabled = true
                day_nineteen_id.isEnabled = false

            } else if (it.day_nineteen == "failed") {
                day_nineteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_id.isEnabled = true
                day_nineteen_id.isEnabled = false
            }

            if (it.day_twenty == "success") {
                day_twenty_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_one_id.isEnabled = true
                day_twenty_id.isEnabled = false

            } else if (it.day_twenty == "failed") {
                day_twenty_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_one_id.isEnabled = true
                day_twenty_id.isEnabled = false
            }

            if (it.day_twenty_one == "success") {
                day_twenty_one_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_two_id.isEnabled = true
                day_twenty_one_id.isEnabled = false

            } else if (it.day_twenty_one == "failed") {
                day_twenty_one_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_two_id.isEnabled = true
                day_twenty_one_id.isEnabled = false
            }

            if (it.day_twenty_two == "success") {
                day_twenty_two_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_three_id.isEnabled = true
                day_twenty_two_id.isEnabled = false

            } else if (it.day_twenty_two == "failed") {
                day_twenty_two_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_three_id.isEnabled = true
                day_twenty_two_id.isEnabled = false
            }

            if (it.day_twenty_three == "success") {
                day_twenty_three_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_four_id.isEnabled = true
                day_twenty_three_id.isEnabled = false

            } else if (it.day_twenty_three == "failed") {
                day_twenty_three_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_four_id.isEnabled = true
                day_twenty_three_id.isEnabled = false
            }

            if (it.day_twenty_four == "success") {
                day_twenty_four_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_five_id.isEnabled = true
                day_twenty_four_id.isEnabled = false

            } else if (it.day_twenty_four == "failed") {
                day_twenty_four_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_five_id.isEnabled = true
                day_twenty_four_id.isEnabled = false
            }

            if (it.day_twenty_five == "success") {
                day_twenty_five_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_six_id.isEnabled = true
                day_twenty_five_id.isEnabled = false

            } else if (it.day_twenty_five == "failed") {
                day_twenty_five_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_six_id.isEnabled = true
                day_twenty_five_id.isEnabled = false
            }

            if (it.day_twenty_six == "success") {
                day_twenty_six_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_seven_id.isEnabled = true
                day_twenty_six_id.isEnabled = false

            } else if (it.day_twenty_six == "failed") {
                day_twenty_six_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_seven_id.isEnabled = true
                day_twenty_six_id.isEnabled = false
            }

            if (it.day_twenty_seven == "success") {
                day_twenty_seven_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_eight_id.isEnabled = true
                day_twenty_seven_id.isEnabled = false

            } else if (it.day_twenty_seven == "failed") {
                day_twenty_seven_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_eight_id.isEnabled = true
                day_twenty_seven_id.isEnabled = false
            }

            if (it.day_twenty_eight == "success") {
                day_twenty_eight_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_nine_id.isEnabled = true
                day_twenty_eight_id.isEnabled = false

            } else if (it.day_twenty_eight == "failed") {
                day_twenty_eight_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_nine_id.isEnabled = true
                day_twenty_eight_id.isEnabled = false
            }

            if (it.day_twenty_nine == "success") {
                day_twenty_nine_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_thirty_id.isEnabled = true
                day_twenty_nine_id.isEnabled = false

            } else if (it.day_twenty_nine == "failed") {
                day_twenty_nine_id.setBackgroundResource(R.drawable.day_background_red)
                day_thirty_id.isEnabled = true
                day_twenty_nine_id.isEnabled = false
            }

            if (it.day_thirty == "success") {
                day_thirty_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_thirty_id.isEnabled = false

            } else if (it.day_thirty == "failed") {
                day_thirty_id.setBackgroundResource(R.drawable.day_background_red)
                day_thirty_id.isEnabled = false
            }
        })
    }

    private fun daysActionButtons() {

        day_one_id.setOnClickListener {
            showAlertDialog("day_one")
        }

        day_two_id.setOnClickListener {
            showAlertDialog("day_two")
        }

        day_three_id.setOnClickListener {
            showAlertDialog("day_three")
        }

        day_four_id.setOnClickListener {
            showAlertDialog("day_four")
        }

        day_five_id.setOnClickListener {
            showAlertDialog("day_five")
        }

        day_six_id.setOnClickListener {
            showAlertDialog("day_six")
        }

        day_seven_id.setOnClickListener {
            showAlertDialog("day_seven")
        }

        day_eight_id.setOnClickListener {
            showAlertDialog("day_eight")
        }

        day_nine_id.setOnClickListener {
            showAlertDialog("day_nine")
        }

        day_ten_id.setOnClickListener {
            showAlertDialog("day_ten")
        }

        day_eleven_id.setOnClickListener {
            showAlertDialog("day_eleven")
        }

        day_tweleve_id.setOnClickListener {
            showAlertDialog("day_twelve")
        }

        day_thirteen_id.setOnClickListener {
            showAlertDialog("day_thirteen")
        }

        day_fourteen_id.setOnClickListener {
            showAlertDialog("day_fourteen")
        }

        day_fifteen_id.setOnClickListener {
            showAlertDialog("day_fifteen")
        }

        day_sixteen_id.setOnClickListener {
            showAlertDialog("day_sixteen")
        }

        day_seventeen_id.setOnClickListener {
            showAlertDialog("day_seventeen")
        }

        day_eighteen_id.setOnClickListener {
            showAlertDialog("day_eighteen")
        }

        day_nineteen_id.setOnClickListener {
            showAlertDialog("day_nineteen")
        }

        day_twenty_id.setOnClickListener {
            showAlertDialog("day_twenty")
        }

        day_twenty_one_id.setOnClickListener {
            showAlertDialog("day_twenty_one")
        }

        day_twenty_two_id.setOnClickListener {
            showAlertDialog("day_twenty_two")
        }

        day_twenty_three_id.setOnClickListener {
            showAlertDialog("day_twenty_three")
        }

        day_twenty_four_id.setOnClickListener {
            showAlertDialog("day_twenty_four")
        }

        day_twenty_five_id.setOnClickListener {
            showAlertDialog("day_twenty_five")
        }

        day_twenty_six_id.setOnClickListener {
            showAlertDialog("day_twenty_six")
        }

        day_twenty_seven_id.setOnClickListener {
            showAlertDialog("day_twenty_seven")
        }

        day_twenty_eight_id.setOnClickListener {
            showAlertDialog("day_twenty_eight")
        }

        day_twenty_nine_id.setOnClickListener {
            showAlertDialog("day_twenty_nine")
        }

        day_thirty_id.setOnClickListener {
            showAlertDialog("day_thirty")
        }
    }

    private fun showAlertDialog(day_name: String) {

        val options = arrayOf("Yes", "No")

        val builder = AlertDialog
            .Builder(this)
            .setTitle("Finished this habit today ?")
            .setCancelable(false)
            .setItems(options) { _, which ->

                if (which == 0) {

                    viewModel.returnedVal.observe(this, Observer {
                        successUpdate(day_name, it)
                    })

                    showAlertDialogButtonClicked("SUCCESS")
                }

                if (which == 1) {

                    viewModel.returnedVal.observe(this, Observer {
                        failedUpdate(day_name, it)
                    })

                    showAlertDialogButtonClicked("FAILED")
                }
            }

        builder.create().show()
    }

    private fun successUpdate(
        dayName: String,
        currentDay: Days
    ) {

        when (dayName) {

            "day_one" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_two_id")!!)

                day_one_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_two_id.setBackgroundResource(R.drawable.current_day_background)
                day_two_id.isEnabled = true
                day_one_id.isEnabled = false
            }

            "day_two" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_three_id")!!)

                day_two_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_three_id.setBackgroundResource(R.drawable.current_day_background)
                day_three_id.isEnabled = true
                day_two_id.isEnabled = false
            }

            "day_three" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_four_id")!!)

                day_three_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_four_id.setBackgroundResource(R.drawable.current_day_background)
                day_four_id.isEnabled = true
                day_three_id.isEnabled = false
            }

            "day_four" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_five_id")!!)

                day_four_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_five_id.setBackgroundResource(R.drawable.current_day_background)
                day_five_id.isEnabled = true
                day_four_id.isEnabled = false
            }

            "day_five" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_six_id")!!)

                day_five_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_six_id.setBackgroundResource(R.drawable.current_day_background)
                day_six_id.isEnabled = true
                day_five_id.isEnabled = false
            }

            "day_six" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_seven_id")!!)

                day_six_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_seven_id.setBackgroundResource(R.drawable.current_day_background)
                day_seven_id.isEnabled = true
                day_six_id.isEnabled = false
            }

            "day_seven" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_eight_id")!!)

                day_seven_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_eight_id.setBackgroundResource(R.drawable.current_day_background)
                day_eight_id.isEnabled = true
                day_seven_id.isEnabled = false
            }

            "day_eight" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_nine_id")!!)

                day_eight_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_nine_id.setBackgroundResource(R.drawable.current_day_background)
                day_nine_id.isEnabled = true
                day_eight_id.isEnabled = false
            }

            "day_nine" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "day_ten_id")!!)

                day_nine_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_ten_id.setBackgroundResource(R.drawable.current_day_background)
                day_ten_id.isEnabled = true
                day_nine_id.isEnabled = false
            }

            "day_ten" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_eleven_id"
                    )!!
                )

                day_ten_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_eleven_id.setBackgroundResource(R.drawable.current_day_background)
                day_eleven_id.isEnabled = true
                day_ten_id.isEnabled = false
            }

            "day_eleven" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_tweleve_id"
                    )!!
                )

                day_eleven_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_tweleve_id.setBackgroundResource(R.drawable.current_day_background)
                day_tweleve_id.isEnabled = true
                day_eleven_id.isEnabled = false
            }

            "day_twelve" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_thirteen_id"
                    )!!
                )

                day_tweleve_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_thirteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_thirteen_id.isEnabled = true
                day_tweleve_id.isEnabled = false
            }

            "day_thirteen" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_fourteen_id"
                    )!!
                )

                day_thirteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_fourteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_fourteen_id.isEnabled = true
                day_thirteen_id.isEnabled = false
            }

            "day_fourteen" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_fifteen_id"
                    )!!
                )

                day_fourteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_fifteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_fifteen_id.isEnabled = true
                day_fourteen_id.isEnabled = false
            }

            "day_fifteen" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_sixteen_id"
                    )!!
                )

                day_fifteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_sixteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_sixteen_id.isEnabled = true
                day_fifteen_id.isEnabled = false
            }

            "day_sixteen" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_seventeen_id"
                    )!!
                )

                day_sixteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_seventeen_id.setBackgroundResource(R.drawable.current_day_background)
                day_seventeen_id.isEnabled = true
                day_sixteen_id.isEnabled = false
            }

            "day_seventeen" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_eighteen_id"
                    )!!
                )

                day_seventeen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_eighteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_eighteen_id.isEnabled = true
                day_seventeen_id.isEnabled = false
            }

            "day_eighteen" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_nineteen_id"
                    )!!
                )

                day_eighteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_nineteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_nineteen_id.isEnabled = true
                day_eighteen_id.isEnabled = false

            }

            "day_nineteen" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_id"
                    )!!
                )

                day_nineteen_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_id.isEnabled = true
                day_nineteen_id.isEnabled = false
            }

            "day_twenty" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_one_id"
                    )!!
                )

                day_twenty_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_one_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_one_id.isEnabled = true
                day_twenty_id.isEnabled = false
            }

            "day_twenty_one" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_two_id"
                    )!!
                )

                day_twenty_one_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_two_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_two_id.isEnabled = true
                day_twenty_one_id.isEnabled = false
            }

            "day_twenty_two" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_three_id"
                    )!!
                )

                day_twenty_two_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_three_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_three_id.isEnabled = true
                day_twenty_two_id.isEnabled = false
            }

            "day_twenty_three" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_four_id"
                    )!!
                )

                day_twenty_three_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_four_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_four_id.isEnabled = true
                day_twenty_three_id.isEnabled = false
            }

            "day_twenty_four" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_five_id"
                    )!!
                )

                day_twenty_four_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_five_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_five_id.isEnabled = true
                day_twenty_four_id.isEnabled = false
            }

            "day_twenty_five" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_six_id"
                    )!!
                )

                day_twenty_five_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_six_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_six_id.isEnabled = true
                day_twenty_five_id.isEnabled = false
            }

            "day_twenty_six" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_seven_id"
                    )!!
                )

                day_twenty_six_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_seven_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_seven_id.isEnabled = true
                day_twenty_six_id.isEnabled = false
            }

            "day_twenty_seven" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_eight_id"
                    )!!
                )

                day_twenty_seven_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_eight_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_eight_id.isEnabled = true
                day_twenty_seven_id.isEnabled = false
            }

            "day_twenty_eight" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_twenty_nine_id"
                    )!!
                )

                day_twenty_eight_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_twenty_nine_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_nine_id.isEnabled = true
                day_twenty_eight_id.isEnabled = false
            }

            "day_twenty_nine" -> {

                viewModel.updateDay(
                    HabitUtils.getSuccessDay(
                        dayName,
                        currentDay,
                        "day_thirty_id"
                    )!!
                )

                day_twenty_nine_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_thirty_id.setBackgroundResource(R.drawable.current_day_background)
                day_thirty_id.isEnabled = true
                day_twenty_nine_id.isEnabled = false
            }

            "day_thirty" -> {

                viewModel.updateDay(HabitUtils.getSuccessDay(dayName, currentDay, "NOTHING")!!)

                day_thirty_id.setBackgroundResource(R.drawable.day_back_groundgreen)
                day_thirty_id.isEnabled = false
            }
        }
    }

    private fun failedUpdate(dayName: String, currentDay: Days) {

        when (dayName) {

            "day_one" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_two_id")!!)

                day_one_id.setBackgroundResource(R.drawable.day_background_red)
                day_two_id.setBackgroundResource(R.drawable.current_day_background)

                day_two_id.isEnabled = true
                day_one_id.isEnabled = false
            }

            "day_two" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_three_id")!!)

                day_two_id.setBackgroundResource(R.drawable.day_background_red)
                day_three_id.setBackgroundResource(R.drawable.current_day_background)
                day_three_id.isEnabled = true
                day_two_id.isEnabled = false
            }

            "day_three" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_four_id")!!)

                day_three_id.setBackgroundResource(R.drawable.day_background_red)
                day_four_id.setBackgroundResource(R.drawable.current_day_background)
                day_four_id.isEnabled = true
                day_three_id.isEnabled = false
            }

            "day_four" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_five_id")!!)

                day_four_id.setBackgroundResource(R.drawable.day_background_red)
                day_five_id.setBackgroundResource(R.drawable.current_day_background)
                day_five_id.isEnabled = true
                day_four_id.isEnabled = false
            }

            "day_five" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_six_id")!!)

                day_five_id.setBackgroundResource(R.drawable.day_background_red)
                day_six_id.setBackgroundResource(R.drawable.current_day_background)
                day_six_id.isEnabled = true
                day_five_id.isEnabled = false
            }

            "day_six" -> {
                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_seven_id")!!)

                day_six_id.setBackgroundResource(R.drawable.day_background_red)
                day_seven_id.setBackgroundResource(R.drawable.current_day_background)
                day_seven_id.isEnabled = true
                day_six_id.isEnabled = false
            }

            "day_seven" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_eight_id")!!)

                day_seven_id.setBackgroundResource(R.drawable.day_background_red)
                day_eight_id.setBackgroundResource(R.drawable.current_day_background)
                day_eight_id.isEnabled = true
                day_seven_id.isEnabled = false
            }

            "day_eight" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_nine_id")!!)

                day_eight_id.setBackgroundResource(R.drawable.day_background_red)
                day_nine_id.setBackgroundResource(R.drawable.current_day_background)
                day_nine_id.isEnabled = true
                day_eight_id.isEnabled = false
            }

            "day_nine" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_ten_id")!!)

                day_nine_id.setBackgroundResource(R.drawable.day_background_red)
                day_ten_id.setBackgroundResource(R.drawable.current_day_background)
                day_ten_id.isEnabled = true
                day_nine_id.isEnabled = false
            }

            "day_ten" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_eleven_id")!!)

                day_ten_id.setBackgroundResource(R.drawable.day_background_red)
                day_eleven_id.setBackgroundResource(R.drawable.current_day_background)
                day_eleven_id.isEnabled = true
                day_ten_id.isEnabled = false
            }

            "day_eleven" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_tweleve_id"
                    )!!
                )

                day_eleven_id.setBackgroundResource(R.drawable.day_background_red)
                day_tweleve_id.setBackgroundResource(R.drawable.current_day_background)
                day_tweleve_id.isEnabled = true
                day_eleven_id.isEnabled = false
            }

            "day_twelve" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_thirteen_id"
                    )!!
                )

                day_tweleve_id.setBackgroundResource(R.drawable.day_background_red)
                day_thirteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_thirteen_id.isEnabled = true
                day_tweleve_id.isEnabled = false
            }

            "day_thirteen" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_fourteen_id"
                    )!!
                )

                day_thirteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_fourteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_fourteen_id.isEnabled = true
                day_thirteen_id.isEnabled = false
            }

            "day_fourteen" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_fifteen_id"
                    )!!
                )

                day_fourteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_fifteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_fifteen_id.isEnabled = true
                day_fourteen_id.isEnabled = false
            }

            "day_fifteen" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_sixteen_id"
                    )!!
                )

                day_fifteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_sixteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_fifteen_id.isEnabled = true
                day_fifteen_id.isEnabled = false
            }

            "day_sixteen" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_seventeen_id"
                    )!!
                )

                day_sixteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_seventeen_id.setBackgroundResource(R.drawable.current_day_background)
                day_seventeen_id.isEnabled = true
                day_sixteen_id.isEnabled = false
            }

            "day_seventeen" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_eighteen_id"
                    )!!
                )

                day_seventeen_id.setBackgroundResource(R.drawable.day_background_red)
                day_eighteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_eighteen_id.isEnabled = true
                day_seventeen_id.isEnabled = false
            }

            "day_eighteen" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_nineteen_id"
                    )!!
                )

                day_eighteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_nineteen_id.setBackgroundResource(R.drawable.current_day_background)
                day_nineteen_id.isEnabled = true
                day_eighteen_id.isEnabled = false
            }

            "day_nineteen" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_twenty_id")!!)

                day_nineteen_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_id.isEnabled = true
                day_nineteen_id.isEnabled = false
            }

            "day_twenty" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_one_id"
                    )!!
                )

                day_twenty_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_one_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_one_id.isEnabled = true
                day_twenty_id.isEnabled = false
            }

            "day_twenty_one" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_two_id"
                    )!!
                )

                day_twenty_one_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_two_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_two_id.isEnabled = true
                day_twenty_one_id.isEnabled = false
            }

            "day_twenty_two" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_three_id"
                    )!!
                )

                day_twenty_two_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_three_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_three_id.isEnabled = true
                day_twenty_two_id.isEnabled = false
            }

            "day_twenty_three" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_four_id"
                    )!!
                )

                day_twenty_three_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_four_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_four_id.isEnabled = true
                day_twenty_three_id.isEnabled = false
            }

            "day_twenty_four" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_five_id"
                    )!!
                )

                day_twenty_four_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_five_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_five_id.isEnabled = true
                day_twenty_four_id.isEnabled = false
            }

            "day_twenty_five" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_six_id"
                    )!!
                )

                day_twenty_five_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_six_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_six_id.isEnabled = true
                day_twenty_five_id.isEnabled = false
            }

            "day_twenty_six" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_seven_id"
                    )!!
                )

                day_twenty_six_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_seven_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_seven_id.isEnabled = true
                day_twenty_six_id.isEnabled = false

            }

            "day_twenty_seven" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_eight_id"
                    )!!
                )

                day_twenty_seven_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_eight_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_eight_id.isEnabled = true
                day_twenty_seven_id.isEnabled = false
            }

            "day_twenty_eight" -> {

                viewModel.updateDay(
                    HabitUtils.getFailedDay(
                        dayName,
                        currentDay,
                        "day_twenty_nine_id"
                    )!!
                )

                day_twenty_eight_id.setBackgroundResource(R.drawable.day_background_red)
                day_twenty_nine_id.setBackgroundResource(R.drawable.current_day_background)
                day_twenty_nine_id.isEnabled = true
                day_twenty_eight_id.isEnabled = false
            }

            "day_twenty_nine" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "day_thirty_id")!!)

                day_twenty_nine_id.setBackgroundResource(R.drawable.day_background_red)
                day_thirty_id.setBackgroundResource(R.drawable.current_day_background)
                day_thirty_id.isEnabled = true
                day_twenty_nine_id.isEnabled = false
            }

            "day_thirty" -> {

                viewModel.updateDay(HabitUtils.getFailedDay(dayName, currentDay, "NOTHING")!!)

                day_thirty_id.setBackgroundResource(R.drawable.day_background_red)
                day_thirty_id.isEnabled = false
            }
        }
    }

    private fun getCurrentDayTv(day: String): TextView {

        val resourceID = resources.getIdentifier(
            day, "id",
            packageName
        )

        return findViewById(resourceID)
    }

    private fun showAlertDialogButtonClicked(status: String) {

        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        var exit: Button? = null

        when (status) {

            "SUCCESS" -> {
                val customLayout: View =
                    LayoutInflater.from(this).inflate(R.layout.custom_success_dialog, null)

                alertDialog.setView(customLayout)

                exit = customLayout.findViewById(R.id.exitSuccessBtn)

            }

            "FAILED" -> {
                val customLayout: View =
                    LayoutInflater.from(this).inflate(R.layout.custom_failed_dialog, null)

                alertDialog.setView(customLayout)

                exit = customLayout.findViewById(R.id.exitFailedBtn)
            }

            "FINISHED" -> {
                val customLayout: View =
                    LayoutInflater.from(this).inflate(R.layout.cutom_finished_habit_dialog, null)

                alertDialog.setView(customLayout)

                val exit: Button = customLayout.findViewById(R.id.exitFinishedBtn)
                exit.setOnClickListener {
                    viewModel.returnedVal.observe(this, Observer {

                        val archivedHabit = ArchivedHabit(
                            name = habitName!!,
                            achievement = it.progress,
                            successDays = it.successDays,
                            failedDays = it.failedDays
                        )

                        viewModel.insertArchivedHabit(archivedHabit)
                        viewModel.deleteHabit(habitId!!)

                    })
                    HabitUtils.showToast(this, "Habit Archived.")
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }

        exit?.setOnClickListener {
            startActivity(
                Intent(this, TracerActivity::class.java)
                    .putExtra("id", habitId)
                    .putExtra("name", habitName)
            )
        }

        var dialog: AlertDialog = alertDialog.create()
        dialog.show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }

}