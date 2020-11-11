package com.simplx.apps.a30daystohabit.pojo

import android.widget.TextView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "days_table")
data class Days(

    @PrimaryKey
    @ForeignKey(
        entity = Habit::class,
        parentColumns = ["ID"],
        childColumns = ["habit_id"],
        onDelete = CASCADE
    )
    @ColumnInfo(name = "habit_id")
    val habit_id: Int,

    @ColumnInfo(name = "day_one")
    val day_one: String,

    @ColumnInfo(name = "day_two")
    val day_two: String,

    @ColumnInfo(name = "day_three")
    val day_three: String,

    @ColumnInfo(name = "day_four")
    val day_four: String,

    @ColumnInfo(name = "day_five")
    val day_five: String,

    @ColumnInfo(name = "day_six")
    val day_six: String,

    @ColumnInfo(name = "day_seven")
    val day_seven: String,

    @ColumnInfo(name = "day_eight")
    val day_eight: String,

    @ColumnInfo(name = "day_nine")
    val day_nine: String,

    @ColumnInfo(name = "day_ten")
    val day_ten: String,

    @ColumnInfo(name = "day_eleven")
    val day_eleven: String,

    @ColumnInfo(name = "day_twelve")
    val day_twelve: String,

    @ColumnInfo(name = "day_thirteen")
    val day_thirteen: String,

    @ColumnInfo(name = "day_fourteen")
    val day_fourteen: String,

    @ColumnInfo(name = "day_fifteen")
    val day_fifteen: String,

    @ColumnInfo(name = "day_sixteen")
    val day_sixteen: String,

    @ColumnInfo(name = "day_seventeen")
    val day_seventeen: String,

    @ColumnInfo(name = "day_eighteen")
    val day_eighteen: String,

    @ColumnInfo(name = "day_nineteen")
    val day_nineteen: String,

    @ColumnInfo(name = "day_twenty")
    val day_twenty: String,

    @ColumnInfo(name = "day_twenty_one")
    val day_twenty_one: String,

    @ColumnInfo(name = "day_twenty_two")
    val day_twenty_two: String,

    @ColumnInfo(name = "day_twenty_three")
    val day_twenty_three: String,

    @ColumnInfo(name = "day_twenty_four")
    val day_twenty_four: String,

    @ColumnInfo(name = "day_twenty_five")
    val day_twenty_five: String,

    @ColumnInfo(name = "day_twenty_six")
    val day_twenty_six: String,

    @ColumnInfo(name = "day_twenty_seven")
    val day_twenty_seven: String,

    @ColumnInfo(name = "day_twenty_eight")
    val day_twenty_eight: String,

    @ColumnInfo(name = "day_twenty_nine")
    val day_twenty_nine: String,

    @ColumnInfo(name = "day_thirty")
    val day_thirty: String,

    @ColumnInfo(name = "progress")
    val progress: Double,

    @ColumnInfo(name = "success_days")
    val successDays: Int,

    @ColumnInfo(name = "failed_days")
    val failedDays: Int,

    @ColumnInfo(name = "current_day")
    val currentDay: String
)