package com.simplx.apps.a30daystohabit.utils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class HabitUtils {

    companion object {

        fun showToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun setAnimation(view: View) {
            val animation: AlphaAnimation = AlphaAnimation(0.0f, 1.0f)
            animation.duration = 1500
            view.startAnimation(animation)
        }

        fun getTypeFace(context: Context): Typeface {
            return Typeface.createFromAsset(context.assets, "fonts/fontfamily.ttf")
        }
    }
}