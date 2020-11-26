package com.simplx.apps.a30daystohabit.ui.trace

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import com.simplx.apps.a30daystohabit.utils.getTypeFace
import com.simplx.apps.a30daystohabit.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_tracert.*

@AndroidEntryPoint
class TracerActivity : AppCompatActivity() {

    lateinit var viewModel: TracerViewModel
    private var habitId: Int? = null
    private var habitName: String? = null

    private var currentDay: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracert)

        viewModel = ViewModelProvider(this).get(TracerViewModel::class.java)

        habitId = intent.extras?.getInt("id")
        habitName = intent.extras?.getString("name")

        ac_Tv.typeface = this.getTypeFace()

        checkFinishedHabit()
        setDaysView()

        setTotalProgressView()
        setSuccessDaysView()
        setFailedDaysView()

        coachFab.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun setTotalProgressView() {
        habitId?.let { viewModel.getTotalProgressById(it) }
        viewModel.totalProgress.observe(this, Observer {
            achievementNumId.text = it.toInt().toString()
            determinateBar.progress = it.toInt()
        })
    }

    private fun setSuccessDaysView() {
        habitId?.let { viewModel.getNumOfSuccessDaysById(it) }
        viewModel.numOfSuccessDays.observe(this, Observer {
            successDayNumId.text = it.toString()
        })
    }

    private fun setFailedDaysView() {
        habitId?.let { viewModel.getNumOfFailedById(it) }
        viewModel.numOfFailedDays.observe(this, Observer {
            failedDaysNumId.text = it.toString()
        })
    }

    private fun checkFinishedHabit() {
        habitId?.let { viewModel.getAllDaysById(it) }
        viewModel.allDays.observe(this, Observer {
            if (it.size == 30) {
                showAlertDialogButtonClicked(resources.getString(R.string.finished))
            } else {
                HabitUtils.getViewsIds(this)[it.size].setBackgroundResource(R.drawable.current_day_background)
            }
        })
    }

    private fun setDaysView() {
        // SET VIEW -- WHEN OPEN THIS ACTIVITY
        habitId?.let { viewModel.getAllDaysById(it) }
        viewModel.allDays.observe(this, Observer {
            for (i in it.indices) {
                if (it[i].day_status == resources.getString(R.string.success)) {
                    HabitUtils.getViewsIds(this)[i].setBackgroundResource(R.drawable.day_back_groundgreen)
                } else if (it[i].day_status == resources.getString(R.string.failed)) {
                    HabitUtils.getViewsIds(this)[i].setBackgroundResource(R.drawable.day_background_red)
                }
            }
        })
    }

    private fun showAlertDialog() {

        val options = arrayOf("Yes", "No")

        val builder = AlertDialog
            .Builder(this)
            .setTitle(resources.getString(R.string.confirm_finished))
            .setCancelable(false)
            .setItems(options) { _, which ->

                if (which == 0) {
                    saveDayStatus(status = resources.getString(R.string.success), progress = 3.3333)
                    showAlertDialogButtonClicked(resources.getString(R.string.success))
                }

                if (which == 1) {
                    saveDayStatus(status = resources.getString(R.string.failed), progress = 0.0)
                    showAlertDialogButtonClicked(resources.getString(R.string.failed))
                }
            }

        builder.create().show()
    }

    private fun saveDayStatus(status: String, progress: Double) {
        // GET CURRENT DAY TO SET IT'S STATUS
        habitId?.let { viewModel.getNumOfDaysById(it) }
        viewModel.numOfDays.observe(this, Observer {
            currentDay = it

            val days: Days? = habitId?.let { it1 ->
                Days(
                    habit_id = it1,
                    day_number = currentDay + 1,
                    day_status = status,
                    progress = progress
                )
            }

            days?.let { it1 -> viewModel.insertDay(it1) }
        })
    }

    private fun showAlertDialogButtonClicked(status: String) {

        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        var exit: Button? = null

        when (status) {

            resources.getString(R.string.success) -> {
                val customLayout: View =
                    LayoutInflater.from(this).inflate(R.layout.custom_success_dialog, null)

                alertDialog.setView(customLayout)

                exit = customLayout.findViewById(R.id.exitSuccessBtn)

            }

            resources.getString(R.string.failed) -> {
                val customLayout: View =
                    LayoutInflater.from(this).inflate(R.layout.custom_failed_dialog, null)

                alertDialog.setView(customLayout)

                exit = customLayout.findViewById(R.id.exitFailedBtn)
            }

            resources.getString(R.string.finished) -> {
                val customLayout: View =
                    LayoutInflater.from(this).inflate(R.layout.cutom_finished_habit_dialog, null)

                alertDialog.setView(customLayout)

                val exitBtn: Button = customLayout.findViewById(R.id.exitFinishedBtn)
                exitBtn.setOnClickListener {
                    setHabitInArchive()

                    habitId?.let { it1 -> viewModel.deleteHabit(it1) }
                    startActivity(Intent(this, MainActivity::class.java))

                    this.showToast(resources.getString(R.string.confirm_archive))
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

        val dialog: AlertDialog = alertDialog.create()
        dialog.show()

    }

    private fun setHabitInArchive() {
        val archivedHabit = habitName?.let {
            ArchivedHabit(
                name = it,
                achievement = achievementNumId.text.toString().toInt(),
                successDays = successDayNumId.text.toString().toInt(),
                failedDays = failedDaysNumId.text.toString().toInt()
            )
        }

        archivedHabit?.let { viewModel.insertArchivedHabit(it) }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}