@file:Suppress("DEPRECATION")

package com.simplx.apps.a30daystohabit.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Typeface
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import android.widget.Toast
import com.simplx.apps.a30daystohabit.reminder.AlarmScheduler
import com.simplx.apps.a30daystohabit.ui.trace.TracerActivity
import kotlinx.android.synthetic.main.activity_tracert.*


fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.startUiAnimation() {
    val animation: AlphaAnimation = AlphaAnimation(0.0f, 1.0f)
    animation.duration = 950
    this.startAnimation(animation)
}

fun Context.getTypeFace(): Typeface {
    return Typeface.createFromAsset(this.assets, "fonts/fontfamily.ttf")
}

fun Context.showDelay(seconds: Long, msg: String) {
    val progress: ProgressDialog = ProgressDialog(this)
    progress.setTitle("30 Days To Habit")
    progress.setMessage(msg)
    progress.show()

    val progressRunnable = Runnable { progress.cancel() }

    val pdCanceller = Handler()
    pdCanceller.postDelayed(progressRunnable, seconds)
}

class HabitUtils {

    companion object {

        const val CHANNEL_ID = "notify_001"

        fun formatTime(hour: Int, minute: Int): String? {

            return if (minute < 10) {
                "$hour:0$minute"
            } else {
                if (hour == 0) {
                    "$hour 0:$minute"
                } else {
                    "$hour:$minute"
                }
            }
        }

        fun createFakeAlarm(context: Context) {
            AlarmScheduler.setAlarm(
                minutes = 22,
                hours = 22,
                habitName = "FakeHabit",
                requestHabitId = 2000,
                motivationMsg = "FakeMsg",
                context = context
            )
        }

        fun cancelFakeAlarm(context: Context) {
            AlarmScheduler.cancelAlarm(
                requestHabitId = 2000,
                context = context
            )
        }

        fun getViewsIds(layout: TracerActivity): ArrayList<TextView> {

            val viewIds: ArrayList<TextView> = ArrayList()

            viewIds.add(layout.day_one_id)
            viewIds.add(layout.day_two_id)
            viewIds.add(layout.day_three_id)
            viewIds.add(layout.day_four_id)
            viewIds.add(layout.day_five_id)

            viewIds.add(layout.day_six_id)
            viewIds.add(layout.day_seven_id)
            viewIds.add(layout.day_eight_id)
            viewIds.add(layout.day_nine_id)
            viewIds.add(layout.day_ten_id)

            viewIds.add(layout.day_eleven_id)
            viewIds.add(layout.day_tweleve_id)
            viewIds.add(layout.day_thirteen_id)
            viewIds.add(layout.day_fourteen_id)
            viewIds.add(layout.day_fifteen_id)

            viewIds.add(layout.day_sixteen_id)
            viewIds.add(layout.day_seventeen_id)
            viewIds.add(layout.day_eighteen_id)
            viewIds.add(layout.day_nineteen_id)
            viewIds.add(layout.day_twenty_id)


            viewIds.add(layout.day_twenty_one_id)
            viewIds.add(layout.day_twenty_two_id)
            viewIds.add(layout.day_twenty_three_id)
            viewIds.add(layout.day_twenty_four_id)
            viewIds.add(layout.day_twenty_five_id)

            viewIds.add(layout.day_twenty_six_id)
            viewIds.add(layout.day_twenty_seven_id)
            viewIds.add(layout.day_twenty_eight_id)
            viewIds.add(layout.day_twenty_nine_id)
            viewIds.add(layout.day_thirty_id)

            return viewIds
        }

    }
}